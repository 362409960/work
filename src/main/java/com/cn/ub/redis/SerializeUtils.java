package com.cn.ub.redis;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {

		Object result = null;

		if (isEmpty(bytes)) {
			return null;
		}

		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;

		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);

			// 反序列化对象
			result = ois.readObject();
		} catch (Exception e) {
			logger.error("Failed to deserialize", e);
		} finally {
			try {
				if (null != ois) {
					ois.close();
				}
				if (null != bis) {
					bis.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {

		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;

		if (object == null) {
			return new byte[0];
		}

		try {
			if (!(object instanceof Serializable)) {
				throw new IllegalArgumentException(
						SerializeUtils.class.getSimpleName() + " requires a Serializable payload "
								+ "but received an object of type [" + object.getClass().getName() + "]");
			}

			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);

			// 序列化
			oos.writeObject(object);
			oos.flush();
			bos.flush();
			result = bos.toByteArray();
		} catch (Exception ex) {
			logger.error("Failed to serialize", ex);
		} finally {
			try {
				if (null != oos) {
					oos.close();
				}
				if (null != bos) {
					bos.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
