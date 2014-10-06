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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class Prompter {

    private static Prompter instance_ = null;

    static {
        instance_ = new Prompter();
    }

    private Prompter() {

    }

    public static Object prompt(PromptOptions options) {
        Object inputObj = null;
        boolean validator = Boolean.TRUE;
        do {
            validator = Boolean.TRUE;
            String input = instance_.readUserInput(options);
            try {
                if (input != null && !input.isEmpty())
                    inputObj = options.type.convert(input);
                else if(options.defaultValue != null)
                    inputObj = options.type.convert(options.defaultValue);

                if (inputObj != null && options.choices != null) {
                    if (!options.choices.contains(inputObj)) {
                        System.out.println("Select from choices");
                        validator = Boolean.FALSE;
                    }
                }

            } catch (PrompterException e) {
                System.out.println("Give input as "+options.type.display());
            }
        } while (!validator || (inputObj==null && options.required));

        return inputObj != null ? inputObj : options.defaultValue;
    }

    private String readUserInput(PromptOptions options) {
        PrintWriter writer = new PrintWriter(System.out);
        writer.print(options.inputMessage);
        if (options.defaultValue != null)
            writer.println(" Default is "+options.defaultValue);
        else
            writer.println();

        if (options.choices != null && !options.choices.isEmpty()) {
            for (Object e : options.choices)
                writer.println(e.toString());
        }
        writer.print("> ");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line  = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line != null ? line.trim() : line;
    }

}