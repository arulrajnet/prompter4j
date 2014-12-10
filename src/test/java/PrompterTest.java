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

import com.github.arulrajnet.PromptOptions;
import com.github.arulrajnet.Prompter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test class for prompter4j
 *
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class PrompterTest {

    public static void main(String[] args) {
        /**
         * Get the Name as String. Required
         */
        String cc = Prompter.prompt(new PromptOptions("Enter your name :").required(Boolean.TRUE));
        System.out.println(cc);

        /**
         * Get the Age as Integer. Required
         */
        int ff = Prompter.prompt(new PromptOptions("Enter your age :").required(Boolean.TRUE).type(Integer.class));
        System.out.println(ff);

        /**
         * Get Date of Birth. Not Required
         */
        Date date = Prompter.prompt(new PromptOptions("Enter your Date of Birth : ").type(Date.class));
        System.out.println(date);

        /**
         * Select a Value from List. Required
         */
        String sex = Prompter.prompt(new PromptOptions("Select Your Sex :").choices(PrompterTest.sex));
        System.out.println(sex);

        /**
         * Select a Value from Enum. Not Required. Default Value set.
         */
        DAY dd = Prompter.prompt(new PromptOptions("Select your day :").
                choices(DAY.class, true).defaultValue(DAY.SUNDAY.toString()).type(DAY.class));
        System.out.println(dd);

        /**
         * Select a Value from Integer Array. Required
         */
        Integer ee = Prompter.prompt(new PromptOptions("Select Any one :").choices(levelArray).required(true).type(Integer.class));
        System.out.println(ee);

        /**
         * Choose a File
         */
        File file = Prompter.prompt(new PromptOptions("Choose a File :")
                .choices(new File(System.getProperty("user.home")), true).type(File.class));
        System.out.println(file);

    }

    enum DAY {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    static Integer[] levelArray = {3, 4, 5};

    static List<String> sex = new ArrayList() {
        {
            add("Male");
            add("Female");
        }
    };

}
