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
package com.github.arulrajnet;

import com.github.arulrajnet.type.InputType;
import com.github.arulrajnet.type.ReflectInputType;
import com.github.arulrajnet.type.StringInputType;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class PromptOptions {

    protected String inputMessage = null;
    protected String defaultValue = null;
    protected Boolean required = Boolean.FALSE;
    protected List choices = null;
    protected InputType type = new StringInputType();
    protected int retryTimes = 3;


    public PromptOptions(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public PromptOptions defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public <E> PromptOptions choices(E... values) {
        this.choices = Arrays.asList(values);
        return this;
    }

    public PromptOptions required(Boolean required) {
        this.required = required;
        return this;
    }

    private <E> ReflectInputType<E> createReflectArgumentType(Class<E> type) {
        return new ReflectInputType<E>(type);
    }

    public <E> PromptOptions type(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (type.isPrimitive()) {
            // Convert primitive type class to its object counterpart
            if (type == boolean.class) {
                this.type = createReflectArgumentType(Boolean.class);
            } else if (type == byte.class) {
                this.type = createReflectArgumentType(Byte.class);
            } else if (type == short.class) {
                this.type = createReflectArgumentType(Short.class);
            } else if (type == int.class) {
                this.type = createReflectArgumentType(Integer.class);
            } else if (type == long.class) {
                this.type = createReflectArgumentType(Long.class);
            } else if (type == float.class) {
                this.type = createReflectArgumentType(Float.class);
            } else if (type == double.class) {
                this.type = createReflectArgumentType(Double.class);
            } else {
                // void and char are not supported.
                // char.class does not have valueOf(String) method
                throw new IllegalArgumentException("unexpected primitive type");
            }
        } else {
            this.type = createReflectArgumentType(type);
        }
        return this;
    }

    protected PromptOptions retryTimes(int noOfTimes) {
        this.retryTimes = noOfTimes;
        return this;
    }
}