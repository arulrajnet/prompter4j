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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class PrompterTest {

    public static void main(String[] args) {
        String cc = (String) Prompter.prompt(new PromptOptions("Enter your name :").required(Boolean.TRUE));
        System.out.println(cc);
        DAY dd = (DAY) Prompter.prompt(new PromptOptions("Select your day :").
                choices(DAY.values()).defaultValue(DAY.SUNDAY.toString()).type(DAY.class));
        System.out.println(dd);
        Integer ee = (Integer)Prompter.prompt(new PromptOptions("Select Any one :").choices(levelArray).required(true).type(Integer.class));
        System.out.println(ee);

        int ff = (Integer) Prompter.prompt(new PromptOptions("Enter your age :").required(Boolean.TRUE).type(Integer.class));
        System.out.println(ff);
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

    static Integer[] levelArray = {3 ,4, 5};

}
