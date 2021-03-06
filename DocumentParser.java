/*******************************************************************************
 * ************************     ADNAN OQUAISH     ******************************
 * *************************     BITS Pilani     *******************************
 *******************************************************************************/
 
package CyberKnight.Recommendation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentParser 
{

    //This variable will hold all terms of each document in an array.
    private List<String[]> termsDocsArray = new ArrayList<String[]>();
    private List<String> allTerms = new ArrayList<String>(); //to hold all terms
    private List<double[]> tfidfDocsVector = new ArrayList<double[]>();
//    private List<String> tfidfDocsVectorNames = new ArrayList<String>();

    // Method to read files and store in array.
    // filePath : source file path --  Generally a folder with the required set of documents
    
    public void parseFiles(String filePath) throws FileNotFoundException, IOException
    {
        File[] allfiles = new File(filePath).listFiles();
        BufferedReader in = null;
        for (File f : allfiles) 
        {
            if (f.getName().endsWith(".txt")) 
            {
                in = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String s = null;
                while ((s = in.readLine()) != null) 
                {
                    sb.append(s);
                }
                String[] tokenizedTerms = sb.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");   //to get individual terms
                for(String term : tokenizedTerms)	//avoid duplicate entries
                {
                	if(!allTerms.contains(term))
                	{
                		allTerms.add(term);
                	}
                }
                termsDocsArray.add(tokenizedTerms);
            }
        }

    }

    /**
     * Method to create termVector according to its tfidf score.
     */
    public void tfIdfCalculator() 
    {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term frequency inverse document frequency        
        for (String[] docTermsArray : termsDocsArray) 
        {
            double[] tfidfvectors = new double[allTerms.size()];
            int count = 0;
            for (String terms : allTerms) 
            {
                tf = new TfIdf().tfCalculator(docTermsArray, terms);
                idf = new TfIdf().idfCalculator(termsDocsArray, terms);
                tfidf = tf * idf;
                tfidfvectors[count] = tfidf;
                count++;
            }
            tfidfDocsVector.add(tfidfvectors);  //storing document vectors;            
        }
    }

    // Method to calculate cosine similarity between all the documents.

    public void getCosineSimilarity() 
    {
        for (int i = 0; i < tfidfDocsVector.size(); i++) 
        {
            for (int j = 0; j < tfidfDocsVector.size(); j++) 
            {
	            if(i!=j)
	                System.out.println("between " + i + " and " + j + "  =  "+ new CosineSimilarity().cosineSimilarity (tfidfDocsVector.get(i),  tfidfDocsVector.get(j)));
            }
        }
    }
}
