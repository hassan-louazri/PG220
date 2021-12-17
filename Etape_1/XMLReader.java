
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

    private static int loop=0;
    static  void readXml(String filename,String name, String type)//name:client/fournisseur
                                                                //type: planche/panneau
    {
        loop=0;
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
                            if(name=="client"){
                                Client c =(Client) readName(reader,type);
                                Test.listClient.add(c);
                            }
                            if(name=="fournisseur"){
                                Supplier s=(Supplier)readName(reader, type);
                               Test.listSupplier.add(s);
                            }
                        }
                    }
                }else{
                        if(reader.getName().toString() == name)
                        {
                            if(name=="client"){
                                Client c =(Client) readName(reader,type);
                                Test.listClient.add(c);
                            }
                            if(name=="fournisseur"){
                                Supplier s=(Supplier)readName(reader, type);
                                Test.listSupplier.add(s);
                            }
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

    static User readName(XMLStreamReader reader, String type) throws XMLStreamException
    {
        List<Planche> listPlanche = new ArrayList<>();
        int id = Integer.parseInt(reader.getAttributeValue(0));

        List<Panneau> listPanneau = new ArrayList<>();
        while(reader.hasNext()) {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {
                if(reader.getName().toString() == type)
                {
                    if(type=="planche"){
                        Planche p = (Planche)readType(reader,type);
                        listPlanche.add(p);
                    }else{
                        Panneau p = (Panneau)readType(reader,type);
                        listPanneau.add(p);
                    }
                }else{
                     loop++;
                    break;
                }

            }
        };
        if(type=="planche")
            return new Client(id,listPlanche);
        else
            return new Supplier(id,listPanneau);
    }

    static Wood readType(XMLStreamReader reader,String type) throws XMLStreamException
    {
        int id = (int)controle_data(reader.getAttributeValue(0),"Integer");
        int number = (int)controle_data(reader.getAttributeValue(1),"Integer");
        String date =reader.getAttributeValue(2);
        Dimension dim;
        double price = (double)controle_data(reader.getAttributeValue(3),"Double");
        double Longeur=0,largeur=0;
        while(reader.hasNext())
        {
            if(reader.next() == XMLStreamConstants.START_ELEMENT)
            {

                if(reader.getName().toString() == "dim"){
                        Longeur = (double)controle_data(reader.getAttributeValue(0), "Double");
                        largeur = (double)controle_data(reader.getAttributeValue(1), "Double");
                    break;
                }

            }
        }
        dim=new Dimension(largeur, Longeur);
        if(type=="planche"){
            Planche p=new Planche(id,number,date, price,dim);
            return p;
        }else{
            Panneau p=new Panneau(id,number,date, price,dim);
            return p;
        }

    }
    static Object controle_data(String data, String type){
        Object r=0;
        if(type=="Double"){
            try {
                    r=Double.parseDouble(data);
                    r= (double)r;
            } catch (Exception e) {
                    r=-1.0;

            }
            return (double)r;
        }else  if(type=="Integer"){
            try {
                    r=Integer.parseInt(data);
                    r= (int)r;
            } catch (Exception e) {
                    r=-1;

            }
            return (int)r;
        }else
            return null;
    }
}
