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

import com.github.arulrajnet.type.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class PromptOptions {

    protected String inputMessage = null;
    protected String defaultValue = null;
    protected Boolean required = Boolean.FALSE;
    protected List choices = null;
    protected Boolean choiceAsNumber = Boolean.FALSE;
    protected InputType type = new StringInputType();
    protected int retryTimes = 3;

    /**
     * @param inputMessage
     */
    public PromptOptions(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    /**
     * @param defaultValue
     * @return
     */
    public PromptOptions defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * @param clazz
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(Class clazz) {
        return choices(clazz.getEnumConstants());
    }

    /**
     * @param clazz
     * @param choiceAsNumber
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(Class clazz, Boolean choiceAsNumber) {
        return choices(clazz.getEnumConstants(), choiceAsNumber);
    }

    /**
     * @param collection
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(Collection collection) {
        return choices(collection.toArray());
    }

    /**
     * @param collection
     * @param choiceAsNumber
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(Collection collection, Boolean choiceAsNumber) {
        return choices(collection.toArray(), choiceAsNumber);
    }

    /**
     * @param folder
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(File folder) {
        return choices(folder, Boolean.FALSE);
    }

    /**
     * @param folder
     * @param choiceAsNumber
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(File folder, Boolean choiceAsNumber) {
        if (folder.exists()) {
            List<File> files = new ArrayList<File>();
            String absPath = folder.getAbsolutePath();
            if (folder.isDirectory()) {
                for (String fileName : folder.list()) {
                    files.add(new File(absPath + File.separator + fileName));
                }
            } else if (folder.isFile()) {
                files.add(folder);
            }
            return choices(files, choiceAsNumber);
        }
        return this;
    }

    /**
     * @param values
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(E[] values) {
        return choices(values, Boolean.FALSE);
    }

    /**
     * The value can be chosen from the array of values.
     * choiceAsNumber is used to select a array position instead of value.
     *
     * @param values
     * @param choiceAsNumber
     * @param <E>
     * @return
     */
    public <E> PromptOptions choices(E[] values, Boolean choiceAsNumber) {
        this.choices = Arrays.asList(values);
        this.choiceAsNumber = choiceAsNumber;
        return this;
    }

    /**
     * The value for the given prompt is mandatory. If you are not given any value it will again ask.
     *
     * @param required
     * @return
     */
    public PromptOptions required(Boolean required) {
        this.required = required;
        return this;
    }

    private <E> ReflectInputType<E> createReflectArgumentType(Class<E> type) {
        return new ReflectInputType<E>(type);
    }

    /**
     * The given input converted into this type.
     *
     * @param type
     * @param <E>
     * @return
     */
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
        } else if (type.isAssignableFrom(File.class)) {
            this.type = new FileInputType();
        } else if(type.isAssignableFrom(Date.class)) {
            this.type = new DateInputType();
        } else {
            this.type = createReflectArgumentType(type);
        }
        return this;
    }

    /**
     * Custom Pattern added with existing patterns.
     * Given Priority to User given Pattern.
     *
     * @param datePattern
     * @param <E>
     * @return
     */
    public <E> PromptOptions addDatePattern(String datePattern) {
        DateInputType.customDateFormats.add(1, new SimpleDateFormat(datePattern));
        return this;
    }

    /**
     * Custom Pattern added and not used the existing patterns.
     *
     * @param datePattern
     * @param <E>
     * @return
     */
    public <E> PromptOptions setDatePattern(String datePattern) {
        DateInputType.customDateFormats.add(1, new SimpleDateFormat(datePattern));
        return this;
    }

    protected PromptOptions retryTimes(int noOfTimes) {
        this.retryTimes = noOfTimes;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PromptOptions{");
        sb.append("inputMessage='").append(inputMessage).append('\'');
        sb.append(", defaultValue='").append(defaultValue).append('\'');
        sb.append(", required=").append(required);
        sb.append(", choices=").append(choices);
        sb.append(", choiceAsNumber=").append(choiceAsNumber);
        sb.append(", type=").append(type);
        sb.append(", retryTimes=").append(retryTimes);
        sb.append('}');
        return sb.toString();
    }
}