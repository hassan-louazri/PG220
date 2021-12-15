package Reader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class XMLReader implements IRead
  {

    @Override
     public void readData(String filename){
      {
        data.clear();  
        try
        {
            FileInputStream file = new FileInputStream(filename);
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(file);
            while(reader.hasNext())
            {
                    if(reader.next() == XMLStreamConstants.START_ELEMENT)
                    {
                      data.add(reader.getName().toString());
                        for (int index=0;index<reader.getAttributeCount() ;index++ ) {
                            data.add(reader.getAttributeValue(index));
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
    };

}
