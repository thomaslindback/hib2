package org.teel.arende2;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <p>
 * [Purpose]<br>
 * TODO: Replace this with a short description of the purpose
 * </p>
 * <p>
 * [Summary]<br>
 * TODO: Replace this with a short summary of responsibilities and offerings
 * </p>
 * <p>
 * [Sample code]<br>
 * </p>
 * <p/>
 * [JavaDoc tags]<br>
 *
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
