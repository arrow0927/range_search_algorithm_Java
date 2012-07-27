package recursiveKDT;

import java.lang.reflect.*;
import java.util.*;
import static recursiveKDT.Utility.*;

public class mainRKDT
{

 
  public static void main(String[] args)
  {
    unimp("test with other point sets");

    //generate random nodes
    int numNodes = 1000000;
    boolean nodesMustBeUnique = false;
    
    pr("Generating list of "+numNodes+" size");
    elapsed();
    
    ArrayList<Node> unsortedNodeList = createListOfnodes(numNodes,
        nodesMustBeUnique);
    pr("done");
    elapsed();
     if (numNodes <= 100)
      pr(unsortedNodeList);

 
    pr("building tree");
    elapsed(false);
        RKDT tree = new RKDT();
    tree.buildTree(unsortedNodeList);
    elapsed();
    pr("done");

    //query region
    IRect q = new IRect(4000, 5000, 4999, 8500);
    pr("Querying the tree now with Query region " + q);

    ArrayList<Node> inRange = tree.getPointsInRange(q);
    elapsed();
    if (inRange.size() <= 50)
    {
      pr("Points in region = ");
      pr(inRange);
    }
    else
      pr("# points in range= " + inRange.size());

  }

}
