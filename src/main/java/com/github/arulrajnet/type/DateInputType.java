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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class DateInputType implements InputType<Date> {

    /**
     * Referred http://en.wikipedia.org/wiki/Date_format_by_country
     */
    public static List<SimpleDateFormat> dateFormats = new LinkedList<SimpleDateFormat>(){
        {
            add(new SimpleDateFormat("dd/MMMM/yyyy"));
            add(new SimpleDateFormat("dd-MMMM-yyyy"));
            add(new SimpleDateFormat("dd MMMM yyyy"));
            add(new SimpleDateFormat("dd.MMMM.yyyy"));

            add(new SimpleDateFormat("dd/MMM/yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
            add(new SimpleDateFormat("dd MMM yyyy"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));

            add(new SimpleDateFormat("dd/MM/yyyy"));
            add(new SimpleDateFormat("dd-MM-yyyy"));
            add(new SimpleDateFormat("dd MM yyyy"));
            add(new SimpleDateFormat("dd.MM.yyyy"));

            add(new SimpleDateFormat("dd/M/yyyy"));
            add(new SimpleDateFormat("dd-M-yyyy"));
            add(new SimpleDateFormat("dd M yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));

            add(new SimpleDateFormat("MMM/dd/yyyy"));
            add(new SimpleDateFormat("MMM-dd-yyyy"));
            add(new SimpleDateFormat("MMM dd yyyy"));
            add(new SimpleDateFormat("MMM.dd.yyyy"));

            add(new SimpleDateFormat("MM/dd/yyyy"));
            add(new SimpleDateFormat("MM-dd-yyyy"));
            add(new SimpleDateFormat("MM dd yyyy"));
            add(new SimpleDateFormat("MM.dd.yyyy"));

            add(new SimpleDateFormat("MMMM yyyy"));
            add(new SimpleDateFormat("MMM yyyy"));
            add(new SimpleDateFormat("yyyy"));

        }
    };

    public static List<SimpleDateFormat> customDateFormats = new LinkedList<SimpleDateFormat>();

    public Date convert(String value) throws PrompterException {

        Date date = null;

        DateInputType.dateFormats.addAll(1, customDateFormats);

        for (SimpleDateFormat format : DateInputType.dateFormats) {
            try {
                date = format.parse(value);
            } catch (ParseException e) {
                continue;
            }
            if(date != null) {
                break;
            }
        }

        return date;
    }

    public String display() {
        return Date.class.getSimpleName();
    }
}