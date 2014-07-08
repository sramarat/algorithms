package misc;

public class ThreadSequencing
{
	volatile int status = 1;

	public static void main(String[] args)
	{
		ThreadSequencing ts = new ThreadSequencing();
		A a = new A(ts);
		B b = new B(ts);
		C c = new C(ts);

		a.start();
		b.start();
		c.start();
	}

	private static class A extends Thread
	{
		private ThreadSequencing ts;

		public A(ThreadSequencing ts)
		{
			this.ts = ts;
		}

		public void run()
		{
			try
			{
				synchronized(ts)
				{
					for(int i = 0; i < 100; i++)
					{
						while(ts.status != 1)
						{
							ts.wait();
						}

						System.out.println("A");
						ts.status = 2;
						ts.notifyAll();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static class B extends Thread
	{
		private ThreadSequencing ts;

		public B(ThreadSequencing ts)
		{
			this.ts = ts;
		}

		public void run()
		{
			try
			{
				synchronized(ts)
				{
					for(int i = 0; i < 100; i++)
					{
						while(ts.status != 2)
						{
							ts.wait();
						}

						System.out.println("B");
						ts.status = 3;
						ts.notifyAll();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static class C extends Thread
	{
		private ThreadSequencing ts;

		public C(ThreadSequencing ts)
		{
			this.ts = ts;
		}

		public void run()
		{
			try
			{
				synchronized(ts)
				{
					for(int i = 0; i < 100; i++)
					{
						while(ts.status != 3)
						{
							ts.wait();
						}

						System.out.println("C");
						ts.status = 1;
						ts.notifyAll();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
