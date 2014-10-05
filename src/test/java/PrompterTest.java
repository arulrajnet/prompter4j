/**
 * Filename : PrompterTest
 * Description :
 * Date : 04 Oct, 2014
 * Owner : Clockwork Interviews Pvt., Ltd.,
 * Project : prompter4j
 * Contact : qube@piqube.com
 * History : 
 */

import com.github.arulrajnet.PromptOptions;
import com.github.arulrajnet.Prompter;

/**
 * @author <a href="mailto:me@arulraj.net">Arul</a>
 */
public class PrompterTest {

    public static void main(String[] args) {
        Integer cc = (Integer) Prompter.prompt(new PromptOptions("Enter your name : ").required(Boolean.TRUE).type(Integer.class));
        System.out.println(cc);
        DAY dd = (DAY) Prompter.prompt(new PromptOptions("Select your day :").
                choices(DAY.values()).defaultValue(DAY.SUNDAY.toString()).type(DAY.class));
        System.out.println(dd);
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

}
