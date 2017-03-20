package annuaire.persistance;

/*
 * @(#)DomEcho01.java	1.9 98/11/10
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;

import annuaire.metier.Site;
import annuaire.persistance.SiteDAO;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import annuaire.utils.DOMUtil;



import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

public class SiteXMLDAO implements SiteDAO {

    // Global value so it can be ref'd by the tree-adapter
    static Document document;
    static String filename;

    public SiteXMLDAO(String fichier) {
    	filename = new String(fichier);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
           DocumentBuilder builder = factory.newDocumentBuilder();
           document = builder.parse( new File(fichier) );
        } catch (SAXException sxe) {
           // Error generated during parsing)
           Exception  x = sxe;
           if (sxe.getException() != null)
               x = sxe.getException();
           x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        } catch (IOException ioe) {
           // I/O error
           ioe.printStackTrace();
        }
    }

    public void addSite(Site item) {
    	Element d = document.getDocumentElement();
    	Element s = document.createElement("Site");
    	d.appendChild(s);

    	Element n = document.createElement("Nom");
    	n.setTextContent(item.getDescription());
    	s.appendChild(n);

    	Element u = document.createElement("URL");
    	u.setTextContent(item.getURL());
    	s.appendChild(u);

    	DOMUtil.writeXmlToFile(filename, document);
    }

    public void deleteSite(Site item) {
    	NodeList d = document.getElementsByTagName("Site");

    	for (int i = 0; i< d.getLength(); i++) {
        	Element s = (Element) d.item(i);
        	if (s.getChildNodes().item(0).getTextContent().equals(item.getDescription()) && s.getChildNodes().item(1).getTextContent().equals(item.getURL())) {
        		s.getParentNode().removeChild(s);
                i--;
        	}
    	}
    	DOMUtil.writeXmlToFile(filename, document);
    }

    public ArrayList<Site> getAllSites() {
    	ArrayList<Site> liste = new ArrayList<Site>();

    	String d = null;
    	String u = null;

    	NodeList nl = document.getDocumentElement().getChildNodes();
    	for (int i = 0; i < nl.getLength(); i++) {
    		if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
    			d = ((Element) nl.item(i).getChildNodes().item(0)).getTextContent();
    			u = ((Element) nl.item(i).getChildNodes().item(1)).getTextContent();
    			liste.add(new Site (d, u));
    		}
    	}
    	return liste;
    }

	public String getInfos() {
		return "DAO XML : " + this.toString() + " - nom du fichier XML = " + filename;
	}
}