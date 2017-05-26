
package com.viglet.vecchio.proxy;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class VigProxy {
	private static final int BUFFER_SIZE = 32768;

	public VigProxy(URL url, OutputStream ops) throws IOException {

		try {

			DataOutputStream out = new DataOutputStream(ops);
			BufferedReader rd = null;
			try {
				URLConnection conn = url.openConnection();
				conn.setDoInput(true);
				// not doing HTTP posts
				conn.setDoOutput(false);
				// System.out.println("Type is: " + conn.getContentType());
				// System.out.println("content length: " +
				// conn.getContentLength());
				// System.out.println("allowed user interaction: " +
				// conn.getAllowUserInteraction());
				// System.out.println("content encoding: " +
				// conn.getContentEncoding());
				// System.out.println("content type: " + conn.getContentType());

				// Get the response
				InputStream is = null;
				try {
					is = conn.getInputStream();
					rd = new BufferedReader(new InputStreamReader(is));
				} catch (IOException ioe) {
					System.out.println("********* IO EXCEPTION **********: " + ioe);
				}
				byte by[] = new byte[BUFFER_SIZE];
				int index = is.read(by, 0, BUFFER_SIZE);
				while (index != -1) {
					out.write(by, 0, index);
					index = is.read(by, 0, BUFFER_SIZE);
				}
				out.flush();

			} catch (Exception e) {
				System.err.println("Encountered exception: " + e);
				out.writeBytes("");
			}

			if (rd != null) {
				rd.close();
			}
			if (out != null) {
				out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		// do nothing.
	}
}
