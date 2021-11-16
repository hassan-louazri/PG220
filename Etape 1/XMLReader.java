
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
   static int loop=0;
    static void readXml(String filename,String name, String type)//name:client/fournisseur
                                                                //type: planche panneau
    {
        try
        {
            
            FileInputStream file = new FileInputStream(filename);
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(file);
            while(reader.hasNext())
            {
                if(loop==0){
                    if(reader.next() == XMLStreamConstants.START_ELEMENT)
                    {
                        if(reader.getName().toString() == name)
                        {
                            Client c = readClient(reader,type);
                        }
                    }
                }else{
                        if(reader.getName().toString() == name)
                        {
                            Client c = readClient(reader,type);
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

    static Client readClient(XMLStreamReader reader, String type) throws XMLStreamException
    {
        List<Planche> listPlanche = new ArrayList<>();
        int id = Integer.parseInt(reader.getAttributeValue(0));

        while(reader.hasNext()) {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {
                if(reader.getName().toString() == type)
                {
                    Planche p = readPlanche(reader);
                    listPlanche.add(p);
                }else{
                     loop++;
                    break;
                }

            }
        };
        return new Client(id,listPlanche);
    }

    static Planche readPlanche(XMLStreamReader reader) throws XMLStreamException
    {
        int id = Integer.parseInt(reader.getAttributeValue(0));
        int number = Integer.parseInt(reader.getAttributeValue(1));
        String date =reader.getAttributeValue(2);
        double price;
        Dimension dim;
        try {
            price = Double.parseDouble(reader.getAttributeValue(3));
        }catch (Exception e){
            price=-1;
        }
        double Longeur=0,largeur=0;
        while(reader.hasNext())
        {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {

                if(reader.getName().toString() == "dim"){
                    Longeur = Double.parseDouble(reader.getAttributeValue(0));
                    largeur = Double.parseDouble(reader.getAttributeValue(1));
                    break;
                }

            }
        }
        dim=new Dimension(largeur, Longeur);
        Planche p=new Planche(id,number,date, price,dim);
        return p;
    }
}
