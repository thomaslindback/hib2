package org.teel.arende2;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**

 * @author $Author: Thomas$
 * @version $Revision: 1 $
 *          </p>
 */
public class IDAdapter extends XmlAdapter<String, Long> {

    @Override
    public Long unmarshal(String string) throws Exception {
        return DatatypeConverter.parseLong(string);
    }

    @Override
    public String marshal(Long value) throws Exception {
        return DatatypeConverter.printLong(value);
    }

}
