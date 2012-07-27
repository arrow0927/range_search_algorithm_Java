package recursiveKDT;

import static recursiveKDT.Utility.*;
import java.util.*;

public class RKDTNodeComparator implements Comparator<Node>
{
  boolean compareX = false;

  public RKDTNodeComparator(boolean _compareX)
  {
    this.compareX = _compareX;
  }

  
  
  public int compare(Node Node1, Node Node2)
  {
    int retVal;
    if (this.compareX)
    {
      //pr("Comparator> Node1.X=" + Node1.getX() + ",Node2.X= " + Node2.getX());
      if (Node1.getX() < Node2.getX())
      {
        retVal = -1;
      }
      else if (Node1.getX() == Node2.getX())
      {
        retVal = 1;//let this be 1 because if its 0 the comparator will
        //eliminate duplicate nodes when it returns the sorted result.
      }
      else
      {
        retVal = 1;
      }
    }
    else
    {
      //pr("Comparator> Node1.Y=" + Node1.getY() + ", Node2.Y= " + Node2.getY());
      if (Node1.getY() < Node2.getY())
      {
        retVal = -1;
      }
      else if (Node1.getY() == Node2.getY())
      {
        retVal = 1; //let this be 1 because if its 0 the comparator will
        //eliminate duplicate nodes when it returns the sorted result.
      }
      else
      {
        retVal = 1;
      }
    }
    //pr("Return Value=" + retVal);
    return retVal;
  }

}
