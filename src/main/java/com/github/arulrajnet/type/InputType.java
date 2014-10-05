package com.github.arulrajnet.type;

import com.github.arulrajnet.PrompterException;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public interface InputType<E> {

    public E convert(String value) throws PrompterException;

}
