package recursiveKDT;

public class IRect
{
  public IRect(int x0, int y0, int x1, int y1)
  {
    min = new IPoint2(x0, y0);
    max = new IPoint2(x1, y1);
  }
  public IRect(IPoint2 min, IPoint2 max)
  {
    this(min.x, min.y, max.x, max.y);
  }
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[" + min + " ... " + max + "]");
    return sb.toString();
  }
  public IPoint2 min, max;
  public int xMax()
  {
    return max.x;
  }
  public int xMin()
  {
    return min.x;
  }
  public int yMax()
  {
    return max.y;
  }
  public int yMin()
  {
    return min.y;
  }
  public int max(boolean c)
  {
    return max.coord(c);
  }
  public int min(boolean c)
  {
    return min.coord(c);
  }
  public boolean contains(int x, int y)
  {
    return x >= min.x && x <= max.x && y >= min.y && y <= max.y;
  }
}
