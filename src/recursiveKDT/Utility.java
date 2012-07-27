package recursiveKDT;

import java.lang.reflect.*;
import java.util.*;

public class Utility
{
  final static boolean DEBUG = true;
  private static Random rnd = new Random(1965);

  public static HashMap<String, Integer> createQueryRegion(int xL, int xH,
      int yL, int yH)
  {
    HashMap<String, Integer> sReg = new HashMap<String, Integer>();
    sReg.put("xL", new Integer(xL));
    sReg.put("xH", new Integer(xH));
    sReg.put("yL", new Integer(yL));
    sReg.put("yH", new Integer(yH));
    return sReg;
  }

  static boolean isPointInRegion(int x, int y, HashMap<String, Integer> sReg)
  {
    boolean inX = ((sReg.get("xL").intValue() <= x) && (sReg.get("xH")
        .intValue() >= x)) ? true : false;
    inX = true;

    boolean inY = ((sReg.get("yL").intValue() <= y) && (sReg.get("yH")
        .intValue() >= y)) ? true : false;

    return (inX && inY);
  }

  public static ArrayList createListOfnodes(int numNodes, boolean uniqueNodes)
  {

    ArrayList a = new ArrayList();
    Set s = new HashSet();
    
    boolean test = false;
    if (test)
warn("always choosing same point");

    for (int i = 0; i < numNodes; i++)
    {
      int x, y;
      String st = null;
      do
      {
        x = rnd.nextInt(4500) + 1000; //generateRandomNumber('X', rnd);
        y = rnd.nextInt(4500) + 5500; //generateRandomNumber('Y', rnd);
        
        if (test) {
          x = rnd.nextInt(3) + 4000; 
          y = rnd.nextInt(3) + 5500;
        }
        
    if (uniqueNodes)    st = "" + x + "," + y;
      }
      while (uniqueNodes && s.contains(st));

   if (uniqueNodes)   s.add(st);
      a.add(new Node(i, x, y));
    }
    pr("a size=" + a.size());

    return a;
  }

  public static ArrayList<Node> manuallycreateListOfNodes(
      boolean generaterepeats, int numNodes)
  {
    ArrayList<Node> tmp = new ArrayList<Node>(0);
    if (!generaterepeats)
    {
      for (int i = 0; i < 10; i++)
      {
        tmp.add(new Node(i, i, 20 - i));
      }

    }
    else
    {
      for (int i = 0; i < 10; i++)
      {
        if (i % 2 == 0)
        {
          tmp.add(new Node(i, 7, 20 - 2));
        }
        else if (i % 3 == 0)
        {
          tmp.add(new Node(i, 9, 20 - 3));
        }
        else
          tmp.add(new Node(i, 11, 20 - i));
      }
    }

    return tmp;
  }

  //returns the random value for X or Y corrdinate
  //integer is an approximation of latitude or longitude values converted to XXX,XXX,XXX
  //  public static int generateRandomNumber(char c, Random r)
  //  {
  //    int val = -1;
  //
  //    if (c == 'X' || c == 'x')
  //    {
  //      //Generate a random value r, such that  49200000 < r <= 49290000
  //      //val = (r.nextInt(99999) + 49200000);
  //      val = r.nextInt(9000) + 1000;
  //    }
  //    else if (c == 'Y' || c == 'y')
  //    {
  //      //Generate a random value r, such that  123000000 < r <= 123200000
  //      //val = (r.nextInt(99999) + 123000000);
  //      val = r.nextInt(9000) + 1000;
  //    }
  //    return val;
  //
  //  }

  public static ArrayList<Node> toSortedArray(int depth, ArrayList<Node> l)
  {
    if (depth % 2 == 0) //sort on x
      //sort this arraylist on X and return it
      return l;
    else
      //sort on y
      return l;
  }

  public static boolean mostSpreadDimensionIsX(ArrayList<Node> L1)
  {
    int maxX = 0;
    int maxY = 0;
    int minX = 0;
    int minY = 0;
    //calculate the difference between min x and max x values
    Iterator<Node> i = L1.iterator();
    while (i.hasNext())
    {
      Node tmp = i.next();
      maxX = maxX < tmp.getX() ? tmp.getX() : maxX;
      minX = minX >= tmp.getX() ? tmp.getX() : minX;

      maxY = maxY < tmp.getY() ? tmp.getY() : maxY;
      minY = minY >= tmp.getY() ? tmp.getY() : minY;
    }
    return ((maxX - minX) > (maxY - minY)) ? true : false;
  }

  public static void pr(Object s)
  {
    System.out.println(s);
  }

  public static void pr(List<Node> l)
  {
    {
      Iterator<Node> i = l.iterator();
      int index = 0;
      while (i.hasNext())
      {
        pr(" #" + index + "" + i.next());
        index++;
      }
    }
  }

  private static String stackTrace(int skip)
  {
    StackTraceElement[] el = Thread.currentThread().getStackTrace();
    int index = skip;
    //el.length - 1 - skip;
    if (index >= el.length)
      return "???";

    String s = el[index].toString();
    int pos = s.indexOf('(');
    int pos2 = s.indexOf(')');
    return s.substring(pos + 1, pos2);

  }

  private static Set warnings = new HashSet();

  public static void unimp(String s)
  {
    if (s == null)
      s = "<unknown>";

    String key = "(" + stackTrace(3) + "): " + s;

    if (warnings.add(key))
    {
      pr("*** Unimplemented " + key);
    }
  }
  private static long prevTm;
  public static void elapsed()
  {
    elapsed(true);
  }
  public static void elapsed(boolean print)
  {
    long tm = System.currentTimeMillis();
    if (print && prevTm != 0)
    {
      pr("Elapsed time= " + (tm - prevTm));
    }
    prevTm = tm;
  }

  public static void warn(String s)
  {
    if (s == null)
      s = "<unknown>";
    String key = "(" + stackTrace(3) + "): " + s;

    if (warnings.add(key))
    {
      pr("*** Warning " + key);
    }
  }
}
