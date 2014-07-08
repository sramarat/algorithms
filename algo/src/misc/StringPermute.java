package misc;

public class StringPermute
{
	public void permute(String inputString)
	{
		int length = inputString.length();
		boolean[] used = new boolean[length];
		char[] in = inputString.toCharArray();
		char[] out = new char[length];

		doPermute(in, used, out, length, 0);
	}

	private void doPermute(char[] in, boolean[] used, char[] out,
			int inputLength, int level)
	{
		if (level == inputLength)
		{
			System.out.println(out);
			return;
		}

		for (int i = 0; i < inputLength; ++i)
		{
			if (used[i])
				continue;

			out[level] = in[i];
			used[i] = true;
			doPermute(in, used, out, inputLength, level + 1);
			used[i] = false;
		}
	}

	public static void main(String[] args)
	{
		String input = "sweat";
		new StringPermute().permute(input);
	}
}
