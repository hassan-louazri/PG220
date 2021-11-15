
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader
{
    static void readXml(String filename)
    {
        try
        {
            FileInputStream file = new FileInputStream(filename);
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(file);
            while(reader.hasNext())
            {
                if(reader.next() == XMLStreamConstants.START_ELEMENT)
                {
                    if(reader.getName().toString() == "client")
                    {
//
                        System.out.println("Client n°"+reader.getAttributeValue(0) );
                        Client c = readClient(reader);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (XMLStreamException e)
        {
            e.printStackTrace();
        }
    }
    static Client readClient(XMLStreamReader reader) throws XMLStreamException
    {
        int id = Integer.parseInt(reader.getAttributeValue(0));
        List<Planche> listPlanche = new ArrayList<>();
        while(reader.hasNext())
        {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {
                if(reader.getName().toString() == "planche")
                {
                    String commande = "Commande n°" + reader.getAttributeValue(0) + " de " + reader.getAttributeValue(1) + " planches à livrer pour le " + reader.getAttributeValue(2) + " au prix maximal de " +reader.getAttributeValue(3);
                    System.out.println(commande);
                    Planche p = readPlanche(reader);
                    listPlanche.add(p);
                }

            }
        }
        return new Client(id,listPlanche);
    }

    static Planche readPlanche(XMLStreamReader reader) throws XMLStreamException
    {

        while(reader.hasNext())
        {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {

                if(reader.getName().toString() != "dim" && reader.getName().toString() != "client")
                {
                    String commande = "Commande n°" + reader.getAttributeValue(0) + " de " + reader.getAttributeValue(1) + " planches à livrer pour le " + reader.getAttributeValue(2) + " au prix maximal de " +reader.getAttributeValue(3);
                    System.out.println(commande);
                }else if(reader.getName().toString() == "dim"){
                    double L = Double.parseDouble(reader.getAttributeValue(0));
                    double l = Double.parseDouble(reader.getAttributeValue(1));
                    System.out.println("dimension L*l=: "+L+"*"+l);

                }else{
                    System.out.println("Client n°"+reader.getAttributeValue(0) );
                }

            }
        }
        return new Planche(8, 7);
    }
}
