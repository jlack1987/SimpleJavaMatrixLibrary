public class Vector2d implements java.io.Serializable
{
	private static final long serialVersionUID = 1376445751990551719L;

	double x, y;

	public Vector2d()
	{
		set(0,0);
	}
	
	public Vector2d(double x, double y)
	{
		set(x, y);
	}

	public Vector2d(Vector2d v)
	{
		set(v);
	}

	public Vector2d(double[] d)
	{
		set(d);
	}

	public Vector2d(float[] f)
	{
		set(f);
	}

	public void set(double[] d)
	{
		if (d.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set(d[0], d[1]);
	}

	public void set(float[] f)
	{
		if (f.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set(f[0], f[1]);
	}

	public void set(Vector2d v)
	{
		set(v.x, v.y);
	}

	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void get(Vector2d v)
	{
		v.x = x;
		v.y = y;
	}
	
	public void get(double[] d)
	{
		d[0] = x;
		d[1] = y;
	}
	
	public void get(float[] f)
	{
		f[0] = (float)x;
		f[1] = (float)y;
	}

	public void add(Vector2d vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
	}

	public void subtract(Vector2d vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
	}

	public double dot(Vector2d vector)
	{
		return this.x * vector.x + this.y * vector.y;
	}

	public double length()
	{
		return Math.sqrt(x * x + y * y);
	}

	public void normalize()
	{
		double length = length();
		x /= length;
		y /= length;
	}

	public void normalize(Vector2d vector)
	{
		if (this != vector)
		{
			set(vector);
			normalize();
		}
		else
		{
			normalize();
		}
	}

	public double lengthSquared()
	{
		return (x * x + y * y);
	}

	public double angle(Vector2d vector1)
	{
		double vDot = this.dot(vector1) / (this.length() * vector1.length());
		if (vDot < -1.0)
			vDot = -1.0;
		if (vDot > 1.0)
			vDot = 1.0;
		return ((double) (Math.acos(vDot)));
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
