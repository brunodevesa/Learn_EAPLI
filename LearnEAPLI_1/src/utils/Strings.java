/**
 *
 */
package utils;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class Strings {

    public static String prettyFormat(final String input) {
        return prettyFormat(input, 2);
    }

    /**
     * returns an XML formated output.
     * <p>
     * based in code from
     * http://stackoverflow.com/questions/139076/how-to-pretty
     * -print-xml-from-java
     *
     * @param input
     * @param indent
     * @return
     */
    public static String prettyFormat(final String input, final int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e); // TODO simple exception handling, please
            // review it
        } catch (TransformerException e) {
            throw new RuntimeException(e); // TODO simple exception handling, please
            // review it
        }
    }

    private Strings() {
        // to make sure this is an utility class
    }
}
