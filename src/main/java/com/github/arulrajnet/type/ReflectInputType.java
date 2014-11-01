/*
 * Copyright (C) 2014 Arulraj Venni
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.arulrajnet.type;

import com.github.arulrajnet.PrompterException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class ReflectInputType<E> implements InputType<E> {

    private Class<E> type;

    public ReflectInputType(Class<E> type) {
        this.type = type;
    }

    /**
     *
     * @param value
     * @return
     * @throws PrompterException
     */
    public E convert(String value) throws PrompterException {
        if (type.isEnum()) {
            try {
                return (E) Enum.valueOf((Class<Enum>) type, value);
            } catch (IllegalArgumentException e) {
                throwPrompterException(value, e);
            }
        }
        Method m = null;
        try {
            m = type.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException e) {
            // If no valueOf static method found, try constructor.
            return convertUsingConstructor(value);
        } catch (SecurityException e) {
            handleInstatiationError(e);
        }
        // Only interested in static valueOf method.
        if (!Modifier.isStatic(m.getModifiers())
                || !type.isAssignableFrom(m.getReturnType())) {
            return convertUsingConstructor(value);
        }
        Object obj = null;
        try {
            obj = m.invoke(null, value);
        } catch (IllegalAccessException e) {
            return convertUsingConstructor(value);
        } catch (IllegalArgumentException e) {
            handleInstatiationError(e);
        } catch (InvocationTargetException e) {
            throwPrompterException(value,
                    e.getCause() == null ? e : e.getCause());
        }
        return (E) obj;
    }

    /**
     *
     * @return
     */
    public String display() {
        return type.getSimpleName();
    }

    /**
     *
     * @param value
     * @return
     * @throws PrompterException
     */
    private E convertUsingConstructor(String value) throws PrompterException {
        E obj = null;
        try {
            obj = type.getConstructor(String.class).newInstance(value);
        } catch (InstantiationException e) {
            handleInstatiationError(e);
        } catch (IllegalAccessException e) {
            handleInstatiationError(e);
        } catch (InvocationTargetException e) {
            throwPrompterException(value,
                    e.getCause() == null ? e : e.getCause());
        } catch (NoSuchMethodException e) {
            handleInstatiationError(e);
        }
        return obj;
    }

    /**
     *
     * @param value
     * @param t
     * @throws PrompterException
     */
    private void throwPrompterException(String value, Throwable t)
            throws PrompterException {
        throw new PrompterException(String.format("could not convert '%s' to %s (%s)", value,
                type.getSimpleName(), t.getMessage()));
    }

    /**
     *
     * @param e
     */
    private void handleInstatiationError(Exception e) {
        throw new IllegalArgumentException("reflect type conversion error", e);
    }
}