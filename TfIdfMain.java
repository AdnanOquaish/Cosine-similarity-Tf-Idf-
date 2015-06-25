/*******************************************************************************
 * ************************     ADNAN OQUAISH     ******************************
 * *************************     BITS Pilani     *******************************
 *******************************************************************************/

package CyberKnight.Recommendation;

import java.io.FileNotFoundException;
import java.io.IOException;


public class TfIdfMain 
{
  
  public static void main(String args[]) throws FileNotFoundException, IOException
  {
      DocumentParser dp = new DocumentParser();
      dp.parseFiles("//home/vasudhaika/vsspl/testfiles"); // give the location of source file
      dp.tfIdfCalculator(); //calculates tfidf
      dp.getCosineSimilarity(); //calculates cosine similarity   
  }
}
