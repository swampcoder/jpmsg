package org.particl.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {

   public static <T> String convertObjectToXML(T object) throws JAXBException {

      StringWriter stringWriter = new StringWriter();
      JAXBContext context = JAXBContext.newInstance(object.getClass());
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(object, stringWriter);
      return stringWriter.toString();
   }

   @SuppressWarnings("unchecked")
   public static <T> T convertXMLToObject(Class<T> clazz, String xml) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(clazz);
      Unmarshaller um = context.createUnmarshaller();
      return (T) um.unmarshal(new StringReader(xml));
   }

   private JaxbUtil() {}
}
