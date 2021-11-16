import javax.sql.rowset.spi.XmlReader;

public class Test{

    public static void main(String[] argvs)
    {
     XMLReader.readXml("clients.xml","client","planche");
     XMLReader.readXml("fournisseurs.xml","fournisseur","panneau");
    }

}

