import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class Transform3dTest
{
	private final int nTests = 200;

	@Test
	public void TestAxisAngleAllZerosAndScale()
	{
		AxisAngled axisAngle = new AxisAngled();
		Transform3d transform = new Transform3d();
		Matrix3d matrix = new Matrix3d();

		for (int i = 0; i < nTests; i++)
		{
			axisAngle.x = 0;
			axisAngle.y = 0;
			axisAngle.z = 0;
			axisAngle.angle = 0;

			transform.set(axisAngle, new Vector3d(0, 0, 0), 3);

			transform.getRotationScale(matrix);

			assertEquals(matrix.m00, 3, 1e-10);
			assertEquals(matrix.m11, 3, 1e-10);
			assertEquals(matrix.m22, 3, 1e-10);

			transform.getRotation(matrix);

			assertEquals(matrix.m00, 1, 1e-10);
			assertEquals(matrix.m11, 1, 1e-10);
			assertEquals(matrix.m22, 1, 1e-10);
		}
	}

	@Test
	public void TestAxisAngleAllZerosWithTranslationAndScale()
	{
		AxisAngled axisAngle = new AxisAngled();
		Transform3d transform = new Transform3d();
		Matrix3d matrix = new Matrix3d();
		Random random = new Random();
		Vector3d vectorCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			axisAngle.x = 0;
			axisAngle.y = 0;
			axisAngle.z = 0;
			axisAngle.angle = 0;
			Vector3d vector = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			transform.set(axisAngle, vector, 3);

			transform.getRotationScale(matrix);

			assertEquals(matrix.m00, 3, 1e-10);
			assertEquals(matrix.m11, 3, 1e-10);
			assertEquals(matrix.m22, 3, 1e-10);

			transform.getRotation(matrix);
			transform.getTranslation(vectorCheck);

			assertEquals(matrix.m00, 1, 1e-10);
			assertEquals(matrix.m11, 1, 1e-10);
			assertEquals(matrix.m22, 1, 1e-10);
			assertVector3dEquals("", vectorCheck, vector, 1e-8);
		}
	}

	@Test
	public void TestAxisAngleAllZerosWithTranslationAndScaleWithFloats()
	{
		AxisAnglef axisAngle = new AxisAnglef();
		Transform3d transform = new Transform3d();
		Matrix3f matrix = new Matrix3f();
		Random random = new Random();
		Vector3f vectorCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			axisAngle.x = 0;
			axisAngle.y = 0;
			axisAngle.z = 0;
			axisAngle.angle = 0;
			Vector3f vector = new Vector3f(random.nextFloat(),
					random.nextFloat(), random.nextFloat());
			transform.set(axisAngle, vector, 3.0f);

			transform.getRotationScale(matrix);

			assertEquals(matrix.m00, 3, 1e-10);
			assertEquals(matrix.m11, 3, 1e-10);
			assertEquals(matrix.m22, 3, 1e-10);

			transform.getRotation(matrix);
			transform.getTranslation(vectorCheck);

			assertEquals(matrix.m00, 1, 1e-10);
			assertEquals(matrix.m11, 1, 1e-10);
			assertEquals(matrix.m22, 1, 1e-10);
			assertVector3fEquals("", vectorCheck, vector, 1e-8);
		}
	}

	@Test
	public void TestAxisAngleAllZerosAndVectorScale()
	{
		AxisAngled axisAngle = new AxisAngled();
		Transform3d transform = new Transform3d();
		Matrix3d matrix = new Matrix3d();

		for (int i = 0; i < nTests; i++)
		{
			axisAngle.x = 0;
			axisAngle.y = 0;
			axisAngle.z = 0;
			axisAngle.angle = 0;

			transform.set(axisAngle, new Vector3d(0, 0, 0), 1, 2, 3);

			transform.getRotationScale(matrix);

			assertEquals(matrix.m00, 1, 1e-10);
			assertEquals(matrix.m11, 2, 1e-10);
			assertEquals(matrix.m22, 3, 1e-10);

			transform.getRotation(matrix);

			assertEquals(matrix.m00, 1, 1e-10);
			assertEquals(matrix.m11, 1, 1e-10);
			assertEquals(matrix.m22, 1, 1e-10);
		}
	}

	@Test
	public void TestMultipleDoubleScales1()
	{
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Vector3d scales = new Vector3d();
		Random random = new Random();

		Matrix3d matrixCheck = new Matrix3d();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scalesCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(matrix, vector, scales.x,
					scales.y, scales.z);

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertMatrix3dEquals("", matrix, matrixCheck, 1e-8);
			assertVector3dEquals("", vectorCheck, vectorCheck, 1e-8);
			assertVector3dEquals("", scales, scalesCheck, 1e-8);
		}
	}

	@Test
	public void TestMultipleDoubleScales2()
	{
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();
		Vector3f scales = new Vector3f();
		Random random = new Random();

		Matrix3f matrixCheck = new Matrix3f();
		Vector3f vectorCheck = new Vector3f();
		Vector3f scalesCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(matrix, vector, scales.x,
					scales.y, scales.z);

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertMatrix3fEquals("", matrix, matrixCheck, 1e-5);
			assertVector3fEquals("", vectorCheck, vectorCheck, 1e-5);
			assertVector3fEquals("", scales, scalesCheck, 1e-5);
		}
	}

	@Test
	public void TestMultipleDoubleScales3()
	{
		AxisAngled axisAngle = new AxisAngled();
		Vector3d vector = new Vector3d();
		Vector3d scales = new Vector3d();
		Random random = new Random();

		AxisAngled axisAngleCheck = new AxisAngled();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scalesCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.sin(theta);
			axisAngle.y = Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = theta;
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(axisAngle, vector,
					scales.x, scales.y, scales.z);

			transform.getRotation(axisAngleCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertEquals(axisAngle.x, axisAngleCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleCheck.angle, 1e-8);
			assertVector3dEquals("", vectorCheck, vectorCheck, 1e-5);
			assertVector3dEquals("", scales, scalesCheck, 1e-5);
		}
	}

	@Test
	public void TestMultipleDoubleScales4()
	{
		AxisAnglef axisAngle = new AxisAnglef();
		Vector3f vector = new Vector3f();
		Vector3f scales = new Vector3f();
		Random random = new Random();

		AxisAnglef axisAngleCheck = new AxisAnglef();
		Vector3f vectorCheck = new Vector3f();
		Vector3f scalesCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(axisAngle, vector,
					scales.x, scales.y, scales.z);

			transform.getRotation(axisAngleCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertEquals(axisAngle.x, axisAngleCheck.x, 1e-5);
			assertEquals(axisAngle.y, axisAngleCheck.y, 1e-5);
			assertEquals(axisAngle.z, axisAngleCheck.z, 1e-5);
			assertEquals(axisAngle.angle, axisAngleCheck.angle, 1e-5);
			assertVector3fEquals("", vectorCheck, vectorCheck, 1e-5);
			assertVector3fEquals("", scales, scalesCheck, 1e-5);
		}
	}
	
	@Test
	public void TestSetWithMatrix3dAndVector3d()
	{
		Random random = new Random();
		Matrix3d m = TestingTools.createRandomRotationMatrixd(random);
		Vector3d v = TestingTools.createRandomVector3d(random);
		Transform3d T = new Transform3d(m,v);
		
		Matrix3d m2 = new Matrix3d();
		Vector3d v2 = new Vector3d();
		
		T.get(m2);
		T.get(v2);
		
		TestingTools.assertMatrix3dEquals("", m, m2, 1e-10);
		TestingTools.assertVector3dEquals("", v, v2, 1e-10);
	}
	
	@Test
	public void TestSetWithMatrix3dAndVector3f()
	{
		Random random = new Random();
		Matrix3f m = TestingTools.createRandomRotationMatrixf(random);
		Vector3f v = TestingTools.createRandomVector3f(random);
		Transform3d T = new Transform3d(m,v);
		
		Matrix3f m2 = new Matrix3f();
		Vector3f v2 = new Vector3f();
		
		T.get(m2);
		T.get(v2);
		
		TestingTools.assertMatrix3fEquals("", m, m2, 1e-6f);
		TestingTools.assertVector3fEquals("", v, v2, 1e-6f);
	}
	
	@Test
	public void TestSetWithMatrix3dAndVector3fAndScale()
	{
		Random random = new Random();
		Matrix3f m = TestingTools.createRandomRotationMatrixf(random);
		Vector3f v = TestingTools.createRandomVector3f(random);
		Vector3f scales = new Vector3f();
		scales.x = 2;
		scales.y = 2;
		scales.z = 2;
		Transform3d T = new Transform3d(m,v,scales);
		
		Matrix3f m2 = new Matrix3f();
		Vector3f v2 = new Vector3f();
		Vector3f scalesCheck = new Vector3f();
		
		T.getRotation(m2);
		T.get(v2);
		T.getScale(scalesCheck);
		
		TestingTools.assertMatrix3fEquals("", m, m2, 1e-6f);
		TestingTools.assertVector3fEquals("", v, v2, 1e-6f);
		TestingTools.assertVector3fEquals("",scalesCheck,scales,1e-6f);
	}

	@Test
	public void TestSetWithQuaterniondAndVector3d()
	{
		Random random = new Random();
		Quaterniond Q = TestingTools.createRandomQuaterniond(random);
		Vector3d v = TestingTools.createRandomVector3d(random);
		
		Transform3d T = new Transform3d(Q,v);
		
		Quaterniond Q2 = new Quaterniond();
		Vector3d v2 = new Vector3d();
		
		T.get(Q2);
		T.get(v2);
		
		TestingTools.assertQuaterniondEquals(Q, Q2, 1e-8);
		TestingTools.assertVector3dEquals("", v, v2, 1e-8);
	}
	
	@Test
	public void TestSetWithQuaternionfAndVector3f()
	{
		Random random = new Random();
		Quaternionf Q = TestingTools.createRandomQuaternionf(random);
		Vector3f v = TestingTools.createRandomVector3f(random);
		
		Transform3d T = new Transform3d(Q,v);
		
		Quaternionf Q2 = new Quaternionf();
		Vector3f v2 = new Vector3f();
		
		T.get(Q2);
		T.get(v2);
		
		TestingTools.assertQuaternionfEquals(Q, Q2, 1e-6);
		TestingTools.assertVector3fEquals("", v, v2, 1e-6);
	}
	
	@Test
	public void TestMultipleDoubleScales5()
	{
		Quaternionf quat = new Quaternionf();
		Vector3f vector = new Vector3f();
		Vector3f scales = new Vector3f();
		Random random = new Random();

		Quaternionf axisAngleCheck = new Quaternionf();
		Vector3f vectorCheck = new Vector3f();
		Vector3f scalesCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			quat.x = random.nextFloat();
			quat.y = random.nextFloat();
			quat.z = random.nextFloat();
			quat.w = random.nextFloat();
			quat.normalize();
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(quat, vector, scales.x,
					scales.y, scales.z);

			transform.getRotation(axisAngleCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertEquals(quat.x, axisAngleCheck.x, 1e-5);
			assertEquals(quat.y, axisAngleCheck.y, 1e-5);
			assertEquals(quat.z, axisAngleCheck.z, 1e-5);
			assertEquals(quat.w, axisAngleCheck.w, 1e-5);
			assertVector3fEquals("", vectorCheck, vectorCheck, 1e-5);
			assertVector3fEquals("", scales, scalesCheck, 1e-5);
		}
	}

	@Test
	public void TestMultipleDoubleScales6()
	{
		Quaterniond quat = new Quaterniond();
		Vector3d vector = new Vector3d();
		Vector3d scales = new Vector3d();
		Random random = new Random();

		Quaterniond axisAngleCheck = new Quaterniond();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scalesCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			quat.x = random.nextFloat();
			quat.y = random.nextFloat();
			quat.z = random.nextFloat();
			quat.w = random.nextFloat();
			quat.normalize();
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(quat, vector, scales.x,
					scales.y, scales.z);

			transform.getRotation(axisAngleCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertEquals(quat.x, axisAngleCheck.x, 1e-5);
			assertEquals(quat.y, axisAngleCheck.y, 1e-5);
			assertEquals(quat.z, axisAngleCheck.z, 1e-5);
			assertEquals(quat.w, axisAngleCheck.w, 1e-5);
			assertVector3dEquals("", vectorCheck, vectorCheck, 1e-5);
			assertVector3dEquals("", scales, scalesCheck, 1e-5);
		}
	}

	@Test
	public void TestMultipleDoubleScales7()
	{
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Vector3d scales = new Vector3d();
		Random random = new Random();

		Matrix3d matrixCheck = new Matrix3d();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scalesCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			randomizeVector(random, scales);

			Transform3d transform = new Transform3d(matrix, vector, scales.x,
					scales.y, scales.z);

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scalesCheck);

			assertMatrix3dEquals("", matrix, matrixCheck, 1e-8);
			assertVector3dEquals("", vectorCheck, vectorCheck, 1e-8);
			assertVector3dEquals("", scales, scalesCheck, 1e-8);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentation()
	{
		Random random = new Random();
		AxisAngled axisAngle = new AxisAngled();
		AxisAngled axisAngleToCheck = new AxisAngled();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.sin(theta);
			axisAngle.y = Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = theta;

			transform.setRotationAndZeroTranslation(axisAngle);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = 0;
			axisAngle.y = Math.cos(theta);
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			transform.setRotation(axisAngle);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			transform.setRotation(axisAngle);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentation2()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		AxisAngled axisAngle = new AxisAngled();
		AxisAngled axisAngleToCheck = new AxisAngled();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.sin(theta);
			axisAngle.y = Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = 0;
			axisAngle.y = Math.cos(theta);
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector, 1.0);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector, 1.0);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentation2WithScale()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		AxisAngled axisAngle = new AxisAngled();
		AxisAngled axisAngleToCheck = new AxisAngled();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.sin(theta);
			axisAngle.y = Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			double scale = random.nextDouble() + 0.1;
			Transform3d transform = new Transform3d(axisAngle, vector,
					scale + 0.1);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = 0;
			axisAngle.y = Math.cos(theta);
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			// Test case with zero scale
			Transform3d transform = new Transform3d(axisAngle, vector,
					random.nextDouble() + 0.1);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					random.nextDouble());
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentation2WithVectorScale()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		AxisAngled axisAngle = new AxisAngled();
		AxisAngled axisAngleToCheck = new AxisAngled();
		RotationMatrixd matrix = new RotationMatrixd();
		Vector3d vectorCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			axisAngle.set(matrix);
			randomizeVector(random, vector);
			Vector3d scale = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			Transform3d transform = new Transform3d(axisAngle, vector, scale);
			transform.getRotation(axisAngleToCheck);
			transform.getScale(vectorCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = 0;
			axisAngle.y = Math.cos(theta);
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			// Test case with zero scale
			Transform3d transform = new Transform3d(axisAngle, vector,
					new Vector3d(random.nextDouble(), random.nextDouble(),
							random.nextDouble()));
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					new Vector3d(random.nextDouble(), random.nextDouble(),
							random.nextDouble()));
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentation3()
	{
		Random random = new Random();
		AxisAngled axisAngle = new AxisAngled();
		AxisAngled axisAngleToCheck = new AxisAngled();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.sin(theta);
			axisAngle.y = Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = theta;

			Transform3d transform = new Transform3d(axisAngle, new Vector3d(0,
					0, 0), 1.0);

			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = 0;
			axisAngle.y = Math.cos(theta);
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			Transform3d transform1 = new Transform3d(axisAngle, new Vector3d(0,
					0, 0), 1.0);

			transform1.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextDouble();
			axisAngle.x = Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = Math.sin(theta);
			axisAngle.angle = theta;

			Transform3d transform2 = new Transform3d(axisAngle, new Vector3d(0,
					0, 0), 1.0);
			transform2.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-8);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-8);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-8);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-8);
		}

	}

	@Test
	public void TestUseAxisAngleRepresentationWithFloats()
	{
		Random random = new Random();
		AxisAnglef axisAngle = new AxisAnglef();
		AxisAnglef axisAngleToCheck = new AxisAnglef();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;

			transform.setRotationAndZeroTranslation(axisAngle);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = 0;
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			transform.setRotationAndZeroTranslation(axisAngle);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			transform.setRotationAndZeroTranslation(axisAngle);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}
	}

	@Test
	public void TestUseAxisAngleRepresentationWithFloats2()
	{
		Random random = new Random();
		AxisAnglef axisAngle = new AxisAnglef();
		AxisAnglef axisAngleToCheck = new AxisAnglef();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = 0;
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector, 1.0);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector, 1.0);
			transform.normalize();
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

	}

	@Test
	public void TestUseAxisAngleRepresentationWithFloats2AndScale()
	{
		Random random = new Random();
		AxisAnglef axisAngle = new AxisAnglef();
		AxisAnglef axisAngleToCheck = new AxisAnglef();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					random.nextDouble() + 0.1);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = 0;
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					random.nextDouble() + 0.1);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					random.nextDouble() + 0.1);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

	}

	@Test
	public void TestUseAxisAngleRepresentationWithFloats2AndVectorScale()
	{
		Random random = new Random();
		AxisAnglef axisAngle = new AxisAnglef();
		AxisAnglef axisAngleToCheck = new AxisAnglef();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					new Vector3f(random.nextFloat(), random.nextFloat(),
							random.nextFloat()));
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = 0;
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					new Vector3f(random.nextFloat(), random.nextFloat(),
							random.nextFloat()));
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(axisAngle, vector,
					new Vector3f(random.nextFloat(), random.nextFloat(),
							random.nextFloat()));
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}
	}
	
	@Test
	public void TestSetRotationWithZeroAxisAngle()
	{
		AxisAngled A = new AxisAngled(0,0,0,Math.PI/4);
		Transform3d T = new Transform3d(A,new Vector3d(),2.0);
		
		Matrix3d R = new Matrix3d();
		
		T.getRotation(R);
		assertTrue(TestingTools.isIdentity(R,1e-8));
	}

	@Test
	public void TestNormalize()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		Transform3d transform = new Transform3d();

		createRandomTransformationMatrix(matrix, random);
		messWithRotationMatrixOrthogonality(matrix, random);

		transform.set(matrix);

		transform.normalize();

		assertTrue(checkOrthogonality(transform));

	}

	@Test
	public void TestUseAxisAngleRepresentationWithFloats3()
	{
		Random random = new Random();
		AxisAnglef axisAngle = new AxisAnglef();
		AxisAnglef axisAngleToCheck = new AxisAnglef();

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.sin(theta);
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = 0;
			axisAngle.angle = (float) theta;

			Transform3d transform = new Transform3d(axisAngle, new Vector3f(0,
					0, 0), 1.0);
			transform.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = 0;
			axisAngle.y = (float) Math.cos(theta);
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			Transform3d transform1 = new Transform3d(axisAngle, new Vector3f(0,
					0, 0), 1.0);
			transform1.normalize();
			transform1.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-3);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-3);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-3);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-3);
		}

		for (int i = 0; i < nTests; i++)
		{
			double theta = 0.01 + (Math.PI - 0.02) * random.nextFloat();
			axisAngle.x = (float) Math.cos(theta);
			axisAngle.y = 0;
			axisAngle.z = (float) Math.sin(theta);
			axisAngle.angle = (float) theta;

			Transform3d transform2 = new Transform3d(axisAngle, new Vector3f(0,
					0, 0), 1.0);
			transform2.normalize();
			transform2.getRotation(axisAngleToCheck);

			assertEquals(axisAngle.x, axisAngleToCheck.x, 1e-4);
			assertEquals(axisAngle.y, axisAngleToCheck.y, 1e-4);
			assertEquals(axisAngle.z, axisAngleToCheck.z, 1e-4);
			assertEquals(axisAngle.angle, axisAngleToCheck.angle, 1e-4);
		}

	}

	@Test
	public void TestGetTransformAsQuaterniondAndVector3d()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);

			transform.get(quatCheck, vec);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
		}
	}

	@Test
	public void TestGetTransformAsQuaterniondAndVector3dWithScale()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Transform3d transform = new Transform3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);
			double scale = 100 * random.nextDouble();
			transform.setScale(scale);

			transform.get(quatCheck, vec);
			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestGetTransformAsQuaterniondAndVector3dWithZeroScale()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Transform3d transform = new Transform3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);
			double scale = 0;
			transform.setScale(scale);

			transform.get(quatCheck, vec);
			transform.getScale(scaleCheck);

			assertEquals(0, quatCheck.x, 1e-10);
			assertEquals(0, quatCheck.y, 1e-10);
			assertEquals(0, quatCheck.z, 1e-10);
			assertEquals(1, quatCheck.w, 1e-10);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestCreateTransformWithQuaterniondAndVector3d()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, trans);
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			Transform3d transform = new Transform3d(quat1, trans, 1.0);

			transform.get(quatCheck, vec);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(vec.x, trans.x, 1e-10);
			assertEquals(vec.y, trans.y, 1e-10);
			assertEquals(vec.z, trans.z, 1e-10);
		}
	}

	@Test
	public void TestCreateTransformWithQuaterniondAndVector3dAndScale()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, trans);
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			double scale = random.nextDouble();
			Transform3d transform = new Transform3d(quat1, trans, scale);

			transform.get(quatCheck, vec);
			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(vec.x, trans.x, 1e-10);
			assertEquals(vec.y, trans.y, 1e-10);
			assertEquals(vec.z, trans.z, 1e-10);
			assertEquals(scaleCheck.x, scale, 1e-6);
			assertEquals(scaleCheck.y, scale, 1e-6);
			assertEquals(scaleCheck.z, scale, 1e-6);
		}
	}

	@Test
	public void TestCreateTransformWithQuaterniondAndVector3dAndVectorScale()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, trans);
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			Vector3d scale = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			Transform3d transform = new Transform3d(quat1, trans, scale);

			transform.get(quatCheck, vec);
			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(vec.x, trans.x, 1e-10);
			assertEquals(vec.y, trans.y, 1e-10);
			assertEquals(vec.z, trans.z, 1e-10);
			assertEquals(scaleCheck.x, scale.x, 1e-6);
			assertEquals(scaleCheck.y, scale.y, 1e-6);
			assertEquals(scaleCheck.z, scale.z, 1e-6);
		}
	}

	@Test
	public void TestCreateTransformWithQuaternionfAndVector3f()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();
		Vector3f vec = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, trans);
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			Transform3d transform = new Transform3d(quat1, trans, 1.0);

			transform.get(quatCheck, vec);

			assertEquals(quat1.x, quatCheck.x, 1e-3);
			assertEquals(quat1.y, quatCheck.y, 1e-3);
			assertEquals(quat1.z, quatCheck.z, 1e-3);
			assertEquals(quat1.w, quatCheck.w, 1e-3);
			assertEquals(vec.x, trans.x, 1e-3);
			assertEquals(vec.y, trans.y, 1e-3);
			assertEquals(vec.z, trans.z, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformWithQuaternionfAndVector3fAndVectorScales()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();
		Vector3f vec = new Vector3f();
		Vector3f scaleCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, trans);
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			Vector3f scale = new Vector3f(random.nextFloat(),
					random.nextFloat(), random.nextFloat());
			Transform3d transform = new Transform3d(quat1, trans, scale);

			transform.get(quatCheck, vec);
			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-3);
			assertEquals(quat1.y, quatCheck.y, 1e-3);
			assertEquals(quat1.z, quatCheck.z, 1e-3);
			assertEquals(quat1.w, quatCheck.w, 1e-3);
			assertEquals(vec.x, trans.x, 1e-3);
			assertEquals(vec.y, trans.y, 1e-3);
			assertEquals(vec.z, trans.z, 1e-3);
			assertVector3fEquals("", scaleCheck, scale, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformWithQuaterniond()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			Transform3d transform = new Transform3d(quat1,
					new Vector3d(0, 0, 0), 1.0);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(0, trans.x, 1e-10);
			assertEquals(0, trans.y, 1e-10);
			assertEquals(0, trans.z, 1e-10);
		}
	}

	@Test
	public void TestSetTransformWithQuaterniond()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(0, trans.x, 1e-10);
			assertEquals(0, trans.y, 1e-10);
			assertEquals(0, trans.z, 1e-10);
		}
	}

	@Test
	public void TestSetTransformWithQuaterniondAndVector3d()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Vector3d vector = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			randomizeVector(random, vector);

			transform.set(quat1, vector);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(vector.x, trans.x, 1e-10);
			assertEquals(vector.y, trans.y, 1e-10);
			assertEquals(vector.z, trans.z, 1e-10);
		}
	}

	@Test
	public void TestSetTransformWithQuaterniondAndVector3dAndScale()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Vector3d trans = new Vector3d();
		Vector3d vector = new Vector3d();
		Quaterniond quatCheck = new Quaterniond();
		Transform3d transform = new Transform3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = random.nextDouble();
			quat1.w = random.nextDouble();
			quat1.normalize();

			double scale = random.nextDouble();
			randomizeVector(random, vector);

			transform.set(quat1, vector, scale);

			transform.get(quatCheck);
			transform.getTranslation(trans);
			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-10);
			assertEquals(quat1.y, quatCheck.y, 1e-10);
			assertEquals(quat1.z, quatCheck.z, 1e-10);
			assertEquals(quat1.w, quatCheck.w, 1e-10);
			assertEquals(vector.x, trans.x, 1e-10);
			assertEquals(vector.y, trans.y, 1e-10);
			assertEquals(vector.z, trans.z, 1e-10);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestSetTransformWithQuaternionf()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			assertEquals(quat1.x, quatCheck.x, 1e-5);
			assertEquals(quat1.y, quatCheck.y, 1e-5);
			assertEquals(quat1.z, quatCheck.z, 1e-5);
			assertEquals(quat1.w, quatCheck.w, 1e-5);
			assertEquals(0, trans.x, 1e-5);
			assertEquals(0, trans.y, 1e-5);
			assertEquals(0, trans.z, 1e-5);
		}
	}

	@Test
	public void TestSetTransformWithQuaternionfAndVector3f()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Vector3f vector = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			randomizeVector(random, vector);

			transform.set(quat1, vector);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			assertEquals(quat1.x, quatCheck.x, 1e-5);
			assertEquals(quat1.y, quatCheck.y, 1e-5);
			assertEquals(quat1.z, quatCheck.z, 1e-5);
			assertEquals(quat1.w, quatCheck.w, 1e-5);
			assertEquals(vector.x, trans.x, 1e-5);
			assertEquals(vector.y, trans.y, 1e-5);
			assertEquals(vector.z, trans.z, 1e-5);
		}
	}

	@Test
	public void TestSetTransformWithQuaternionfAndVector3fAndScale()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Vector3f vector = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();
		Transform3d transform = new Transform3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			randomizeVector(random, vector);

			double scale = random.nextDouble();
			transform.set(quat1, vector, scale);

			transform.get(quatCheck);
			transform.getTranslation(trans);

			transform.getScale(scaleCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-5);
			assertEquals(quat1.y, quatCheck.y, 1e-5);
			assertEquals(quat1.z, quatCheck.z, 1e-5);
			assertEquals(quat1.w, quatCheck.w, 1e-5);
			assertEquals(vector.x, trans.x, 1e-5);
			assertEquals(vector.y, trans.y, 1e-5);
			assertEquals(vector.z, trans.z, 1e-5);
			assertEquals(scale, scaleCheck.x, 1e-3);
			assertEquals(scale, scaleCheck.y, 1e-3);
			assertEquals(scale, scaleCheck.z, 1e-3);
		}
	}

	@Test
	public void TestDeterminant()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			transform.set(matrix);
			assertEquals(matrix.determinant(), transform.determinant(), 1e-8);
		}
	}

	@Test
	public void TestDeterminantWithScaledTransform()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			double scale = random.nextDouble();

			Transform3d transform = new Transform3d(matrix, vector, scale);
			matrix.scale(scale);
			Transform3d transform2 = new Transform3d(matrix, vector, 1.0);

			assertEquals(transform2.determinant(), transform.determinant(),
					1e-8);
		}
	}

	@Test
	public void TestCreateTransformWithQuaternionf()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Vector3f trans = new Vector3f();
		Quaternionf quatCheck = new Quaternionf();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			Transform3d transform = new Transform3d(quat1,
					new Vector3f(0, 0, 0), 1.0);

			transform.get(quatCheck, trans);

			assertEquals(quat1.x, quatCheck.x, 1e-3);
			assertEquals(quat1.y, quatCheck.y, 1e-3);
			assertEquals(quat1.z, quatCheck.z, 1e-3);
			assertEquals(quat1.w, quatCheck.w, 1e-3);
			assertEquals(0, trans.x, 1e-3);
			assertEquals(0, trans.y, 1e-3);
			assertEquals(0, trans.z, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformWithQuaternionf2()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Quaternionf quatCheck = new Quaternionf();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			Transform3d transform = new Transform3d(quat1,
					new Vector3f(0, 0, 0), 1.0);

			transform.get(quatCheck);

			assertEquals(quat1.x, quatCheck.x, 1e-3);
			assertEquals(quat1.y, quatCheck.y, 1e-3);
			assertEquals(quat1.z, quatCheck.z, 1e-3);
			assertEquals(quat1.w, quatCheck.w, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformWithQuatWithZeroVectorElement()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextDouble();
			quat1.y = random.nextDouble();
			quat1.z = 0;
			quat1.w = random.nextDouble();
			quat1.normalize();

			transform.setRotationAndZeroTranslation(quat1);

			transform.get(quatCheck, vec);

			assertEquals(quat1.x, quatCheck.x, 1e-7);
			assertEquals(quat1.y, quatCheck.y, 1e-7);
			assertEquals(quat1.z, quatCheck.z, 1e-7);
			assertEquals(quat1.w, quatCheck.w, 1e-7);
		}
	}

	@Test
	public void TestCreateTransformWithQuatWithZeroScalarElement()
	{
		Random random = new Random();
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quatCheck = new Quaterniond();
		Vector3d vec = new Vector3d();
		Transform3d transform = new Transform3d();

		quat1.x = random.nextDouble();
		quat1.y = random.nextDouble();
		quat1.z = random.nextDouble();
		quat1.w = 0;
		quat1.normalize();

		transform.setRotationAndZeroTranslation(quat1);

		transform.get(quatCheck, vec);

		assertEquals(quat1.x, quatCheck.x, 1e-7);
		assertEquals(quat1.y, quatCheck.y, 1e-7);
		assertEquals(quat1.z, quatCheck.z, 1e-7);
		assertEquals(quat1.w, quatCheck.w, 1e-7);
	}

	@Test
	public void TestGetTransformAsQuaternionfAndVector3f()
	{
		Random random = new Random();
		Quaternionf quat1 = new Quaternionf();
		Transform3d transform = new Transform3d();
		Quaternionf quatCheck = new Quaternionf();
		Vector3f vec = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			quat1.x = random.nextFloat();
			quat1.y = random.nextFloat();
			quat1.z = random.nextFloat();
			quat1.w = random.nextFloat();
			quat1.normalize();

			transform.setRotation(quat1);

			transform.get(quatCheck, vec);

			// Having trouble getting quat.w to be more precise than 1e-3 here.
			// It has to be floating point precision issue, but haven't figure
			// out how to fix that.
			assertEquals(quat1.x, quatCheck.x, 1e-3);
			assertEquals(quat1.y, quatCheck.y, 1e-3);
			assertEquals(quat1.z, quatCheck.z, 1e-3);
			assertEquals(quat1.w, quatCheck.w, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformFromMatrix3dAndGetTransformAsMatrix3d()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Matrix3d matrixCheck = new Matrix3d();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(matrix, vector, 1.0);

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scaleCheck);

			assertMatrix3dEquals("", matrixCheck, matrixCheck, 1e-20);
			assertVector3dEquals("", vectorCheck, vector, 1e-20);
			assertEquals(scaleCheck.x, 1.0, 1e-6);
			assertEquals(scaleCheck.y, 1.0, 1e-6);
			assertEquals(scaleCheck.z, 1.0, 1e-6);
		}
	}

	@Test
	public void TestSetMatrixScaleWithMatrix3d()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Matrix3d matrixCheck = new Matrix3d();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			Transform3d transform = new Transform3d(matrix, vector, 1.0);

			double scale = random.nextDouble();
			matrix.scale(scale);

			transform.setRotationScale(matrix);

			transform.getRotationScale(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scaleCheck);

			assertMatrix3dEquals("", matrix, matrixCheck, 1e-8);
			assertVector3dEquals("", vectorCheck, vector, 1e-8);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestSetMatrixScaleWithMatrix3f()
	{
		Random random = new Random();
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();
		Matrix3f matrixCheck = new Matrix3f();
		Vector3f vectorCheck = new Vector3f();
		Vector3f scaleCheck = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(matrix, vector, 1.0f);

			double scale = random.nextDouble();
			matrix.scale((float) scale);

			transform.setRotationScale(matrix);

			transform.getRotationScale(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scaleCheck);

			assertMatrix3fEquals("", matrix, matrixCheck, 1e-3);
			assertVector3fEquals("", vectorCheck, vector, 1e-3);
			assertEquals(scale, scaleCheck.x, 1e-3);
			assertEquals(scale, scaleCheck.y, 1e-3);
			assertEquals(scale, scaleCheck.z, 1e-3);
		}
	}

	@Test
	public void TestCreateTransformFromMatrix3dAndGetTransformAsMatrix3dWithScale()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Matrix3d matrixCheck = new Matrix3d();
		Vector3d vectorCheck = new Vector3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);
			double scale = random.nextDouble();
			Transform3d transform = new Transform3d(matrix, vector, scale);

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);
			transform.getScale(scaleCheck);

			assertMatrix3dEquals("", matrixCheck, matrixCheck, 1e-20);
			assertVector3dEquals("", vectorCheck, vector, 1e-20);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestCreateFromMatrix3d()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Matrix4d matrix4 = new Matrix4d();
		Matrix4d matrixCheck = new Matrix4d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			matrix4.m00 = matrix.m00;
			matrix4.m01 = matrix.m01;
			matrix4.m02 = matrix.m02;
			matrix4.m10 = matrix.m10;
			matrix4.m11 = matrix.m11;
			matrix4.m12 = matrix.m12;
			matrix4.m20 = matrix.m20;
			matrix4.m21 = matrix.m21;
			matrix4.m22 = matrix.m22;
			matrix4.m33 = 1;

			Transform3d transform = new Transform3d(matrix, new Vector3d(0, 0,
					0), 1.0);
			transform.get(matrixCheck);

			assertMatrix4dEquals("", matrixCheck, matrix4, 1e-12);
		}
	}

	@Test
	public void TestSetAsMatrix3d()
	{
		Random random = new Random();
		RotationMatrixd matrix = new RotationMatrixd();
		Matrix4d matrix4 = new Matrix4d();
		Matrix4d matrixCheck = new Matrix4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			matrix4.m00 = matrix.m00;
			matrix4.m01 = matrix.m01;
			matrix4.m02 = matrix.m02;
			matrix4.m03 = 0;
			matrix4.m10 = matrix.m10;
			matrix4.m11 = matrix.m11;
			matrix4.m12 = matrix.m12;
			matrix4.m13 = 0;
			matrix4.m20 = matrix.m20;
			matrix4.m21 = matrix.m21;
			matrix4.m22 = matrix.m22;
			matrix4.m23 = 0;
			matrix4.m30 = 0;
			matrix4.m31 = 0;
			matrix4.m32 = 0;
			matrix4.m33 = 1;

			transform.setRotationAndZeroTranslation(matrix);

			transform.get(matrixCheck);

			assertMatrix4dEquals("", matrixCheck, matrix4, 1e-12);
		}
	}

	@Test
	public void TestSetAsVector3d()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		Matrix4d matrix4 = new Matrix4d();
		Matrix4d matrixCheck = new Matrix4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, vector);

			transform.setTranslationAndIdentityRotation(vector);
			matrix4.m00 = 1;
			matrix4.m11 = 1;
			matrix4.m22 = 1;
			matrix4.m33 = 1;
			matrix4.m03 = vector.x;
			matrix4.m13 = vector.y;
			matrix4.m23 = vector.z;

			transform.get(matrixCheck);

			assertMatrix4dEquals("", matrixCheck, matrix4, 1e-12);
		}
	}

	@Test
	public void TestSetAsVector3f()
	{
		Random random = new Random();
		Vector3f vector = new Vector3f();
		Matrix4f matrix4 = new Matrix4f();
		Matrix4f matrixCheck = new Matrix4f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(random, vector);

			transform.setTranslationAndIdentityRotation(vector);
			matrix4.m00 = 1;
			matrix4.m11 = 1;
			matrix4.m22 = 1;
			matrix4.m33 = 1;
			matrix4.m03 = vector.x;
			matrix4.m13 = vector.y;
			matrix4.m23 = vector.z;

			transform.get(matrixCheck);

			assertMatrix4fEquals("", matrixCheck, matrix4, 1e-5);
		}
	}

	@Test
	public void TestSetAsMatrix3f()
	{
		Random random = new Random();
		RotationMatrixf matrix = new RotationMatrixf();
		Matrix4f matrix4 = new Matrix4f();
		Matrix4f matrixCheck = new Matrix4f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			matrix4.m00 = matrix.m00;
			matrix4.m01 = matrix.m01;
			matrix4.m02 = matrix.m02;
			matrix4.m03 = 0;
			matrix4.m10 = matrix.m10;
			matrix4.m11 = matrix.m11;
			matrix4.m12 = matrix.m12;
			matrix4.m13 = 0;
			matrix4.m20 = matrix.m20;
			matrix4.m21 = matrix.m21;
			matrix4.m22 = matrix.m22;
			matrix4.m23 = 0;
			matrix4.m30 = 0;
			matrix4.m31 = 0;
			matrix4.m32 = 0;
			matrix4.m33 = 1;

			transform.setRotationAndZeroTranslation(matrix);

			transform.get(matrixCheck);

			assertMatrix4fEquals("", matrixCheck, matrix4, 1e-5);
		}
	}

	@Test
	public void TestCreateFromMatrix3d2()
	{
		Random random = new Random();
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Vector3d vectorCheck = new Vector3d();
		Matrix3d matrixCheck = new Matrix3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(matrix, vector, 1.0);
			transform.normalize();
			transform.get(matrixCheck, vectorCheck);

			assertMatrix3dEquals("", matrixCheck, matrix, 1e-12);
			assertVector3dEquals("", vectorCheck, vector, 1e-12);

			transform.get(matrixCheck);

			assertMatrix3dEquals("", matrixCheck, matrix, 1e-12);

			transform.get(vectorCheck);

			assertVector3dEquals("", vectorCheck, vector, 1e-12);
		}
	}

	@Test
	public void TestCreateFromMatrix3f()
	{
		Random random = new Random();
		Matrix3f matrix = new Matrix3f();
		Matrix4f matrix4 = new Matrix4f();
		Matrix4f matrixCheck = new Matrix4f();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			matrix4.m00 = matrix.m00;
			matrix4.m01 = matrix.m01;
			matrix4.m02 = matrix.m02;
			matrix4.m10 = matrix.m10;
			matrix4.m11 = matrix.m11;
			matrix4.m12 = matrix.m12;
			matrix4.m20 = matrix.m20;
			matrix4.m21 = matrix.m21;
			matrix4.m22 = matrix.m22;
			matrix4.m33 = 1;

			Transform3d transform = new Transform3d(matrix, new Vector3f(0, 0,
					0), 1.0);
			transform.get(matrixCheck);

			assertMatrix4fEquals("", matrixCheck, matrix4, 1e-6);
		}
	}

	@Test
	public void TestCreateFromMatrix3f2()
	{
		Random random = new Random();
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();
		Vector3f vectorCheck = new Vector3f();
		Matrix3f matrixCheck = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);

			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(matrix, vector, 1.0);
			transform.get(matrixCheck, vectorCheck);

			assertMatrix3fEquals("", matrixCheck, matrix, 1e-6);
			assertVector3fEquals("", vectorCheck, vector, 1e-6);

			transform.get(matrixCheck);
			assertMatrix3fEquals("", matrixCheck, matrix, 1e-6);

			transform.get(vectorCheck);
			assertVector3fEquals("", vectorCheck, vector, 1e-6);
		}
	}

	@Test
	public void TestCreateFromTransform()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		Matrix4d matrixCheck = new Matrix4d();

		createRandomTransformationMatrix(matrix, random);
		Transform3d transform = new Transform3d(matrix);
		Transform3d transformCheck = new Transform3d(transform);

		transform.get(matrix);
		transformCheck.get(matrixCheck);

		assertMatrix4dEquals("", matrixCheck, matrix, 1e-12);
	}

	@Test
	public void TestCreateFromMatrix3fAndVector3f()
	{
		Random random = new Random();
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);

			Transform3d transform = new Transform3d(matrix, vector, 1.0);
			Matrix3f matrixCheck = new Matrix3f();
			Vector3f vectorCheck = new Vector3f();

			transform.getRotation(matrixCheck);
			transform.getTranslation(vectorCheck);

			assertMatrix3fEquals("", matrixCheck, matrixCheck, 1e-20);
			assertVector3fEquals("", vectorCheck, vector, 1e-20);
		}
	}

	@Test
	public void TestCreateIdentityTransform()
	{
		for (int j = 0; j < nTests; j++)
		{
			double[] identityMatrix4By4 = new double[16];

			identityMatrix4By4[0] = 1;
			identityMatrix4By4[5] = 1;
			identityMatrix4By4[10] = 1;
			identityMatrix4By4[15] = 1;

			Transform3d transform3d = new Transform3d();

			double error = 0;
			;
			double[] doubleTransform = new double[16];
			transform3d.get(doubleTransform);
			for (int i = 0; i < 16; i++)
			{
				error += (identityMatrix4By4[i] - doubleTransform[i]);
			}

			assertTrue(error == 0);
		}
	}

	@Test
	public void TestCreateFromMatrix4d()
	{
		Matrix4d matrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			Transform3d transform3d = new Transform3d(matrix);

			Matrix4d check = new Matrix4d();

			transform3d.get(check);

			assertMatrix4dEquals("", check, matrix, 1e-20);
		}
	}

	@Test
	public void TestCreateFromColumnMajorMatrix4d()
	{
		Matrix4d matrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			matrix.transpose();
			Transform3d transform3d = new Transform3d();
			transform3d.setAsTranspose(matrix);
			matrix.transpose();

			Matrix4d check = new Matrix4d();

			transform3d.get(check);

			assertMatrix4dEquals("", check, matrix, 1e-20);
		}
	}

	@Test
	public void TestCreateFromColumnMajorMatrix4()
	{
		Matrix4f matrix = new Matrix4f();
		Random random = new Random();
		float[] columnMajorTransformationMatrix = new float[16];

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			matrix.transpose();
			createFloatArrayFromMatrix4d(columnMajorTransformationMatrix,
					matrix);
			Transform3d transform3d = new Transform3d();

			transform3d.setAsTranspose(columnMajorTransformationMatrix);

			Matrix4f check = new Matrix4f();

			transform3d.get(check);

			matrix.transpose();
			assertMatrix4fEquals("", check, matrix, 1e-6);
		}
	}

	@Test
	public void TestCreateFromColumnMajorMatrix4f()
	{
		Matrix4f matrix = new Matrix4f();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			matrix.transpose();
			Transform3d transform3d = new Transform3d();
			transform3d.setAsTranspose(matrix);
			matrix.transpose();

			Matrix4f check = new Matrix4f();

			transform3d.get(check);

			assertMatrix4fEquals("", check, matrix, 1e-10);
		}
	}

	@Test
	public void TestCreateFromMatrix4f()
	{
		Matrix4f matrix = new Matrix4f();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			Transform3d transform3d = new Transform3d(matrix);

			Matrix4f check = new Matrix4f();

			transform3d.get(check);

			assertMatrix4fEquals("", check, matrix, 1e-8);
		}
	}

	@Test
	public void TestCreateFromDenseMatrix()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);

			Transform3d transform3d = new Transform3d(denseMatrix);

			Matrix4d checkMatrix = new Matrix4d();

			transform3d.get(checkMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestCreateFromDenseMatrixAndVector3d()
	{
		Matrix3d denseMatrix = new Matrix3d();
		Vector3d trans = new Vector3d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(denseMatrix, random);
			randomizeVector(random, trans);

			Transform3d transform3d = new Transform3d(denseMatrix, trans, 1.0);

			Matrix3d checkMatrix = new Matrix3d();
			Vector3d checkVector = new Vector3d();

			transform3d.getRotation(checkMatrix);
			transform3d.getTranslation(checkVector);

			assertMatrix3dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestCreateFromDenseMatrixAndVector3dWithVectorScale()
	{
		Matrix3d denseMatrix = new Matrix3d();
		Vector3d trans = new Vector3d();
		Vector3d scales = new Vector3d();
		Vector3d checkScales = new Vector3d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(denseMatrix, random);
			randomizeVector(random, trans);

			scales.x = random.nextDouble();
			scales.y = random.nextDouble();
			scales.z = random.nextDouble();
			Transform3d transform3d = new Transform3d(denseMatrix, trans,
					scales);

			Matrix3d checkMatrix = new Matrix3d();
			Vector3d checkVector = new Vector3d();

			transform3d.getRotation(checkMatrix);
			transform3d.getTranslation(checkVector);
			transform3d.getScale(checkScales);

			assertMatrix3dEquals("",denseMatrix, checkMatrix, 1e-15);
			assertVector3dEquals("", checkScales, scales, 1e-8);
		}
	}

	@Test
	public void TestCreateFromDoubleArray()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		double[] doubleArrayCheck = new double[16];

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			double[] matrixAsDoubleArray = new double[16];
			matrix.get(matrixAsDoubleArray);
			Transform3d transform = new Transform3d(matrixAsDoubleArray);

			transform.get(doubleArrayCheck);
			assertArrayEquals(matrixAsDoubleArray, doubleArrayCheck, 1e-12);
		}
	}

	@Test
	public void TestMax()
	{
		double a = 1;
		double b = 2;
		double c = 3;

		assertEquals(Transform3d.max3(a, b, c), c, 1e-12);

		a = 2;
		b = 3;
		c = 1;

		assertEquals(Transform3d.max3(a, b, c), b, 1e-12);

		a = 3;
		b = 1;
		c = 2;

		assertEquals(Transform3d.max3(a, b, c), a, 1e-12);
	}

	@Test
	public void TestCreateFromfloatArray()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		float[] floatArray = new float[16];

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			double[] matrixAsFloatArray = new double[16];
			matrix.get(matrixAsFloatArray);
			float[] floatArrayCheck = putDoublesInFloatArray(matrixAsFloatArray);

			Transform3d transform = new Transform3d(floatArrayCheck);

			transform.get(floatArray);

			assertArrayEquals(floatArray, floatArrayCheck, (float) 1e-6);
		}
	}

	@Test
	public void TestTransformMultiplication()
	{
		Random random = new Random();
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();

		for (int i = 0; i < 1; i++)
		{
			createRandomTransformationMatrix(matrix1, random);
			createRandomTransformationMatrix(matrix2, random);

			Transform3d transform1 = new Transform3d(matrix1);
			Transform3d transform2 = new Transform3d(matrix2);

			matrix1.multiply(matrix2);
			transform1.multiply(transform2);

			Matrix4d transformMat4d = new Matrix4d();

			transform1.get(transformMat4d);

			assertMatrix4dEquals("", matrix1, transformMat4d, 1e-15);
		}
	}

	@Test
	public void TestTransformMultiplication2()
	{
		Random random = new Random();
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < 1; i++)
		{
			createRandomTransformationMatrix(matrix1, random);
			createRandomTransformationMatrix(matrix2, random);

			Transform3d transform1 = new Transform3d(matrix1);
			Transform3d transform2 = new Transform3d(matrix2);

			matrix1.multiply(matrix2);
			transform.multiply(transform1, transform2);

			Matrix4d transformMat4d = new Matrix4d();

			transform.get(transformMat4d);

			assertMatrix4dEquals("", matrix1, transformMat4d, 1e-15);
		}
	}

	@Test
	public void TestTransformMultiplication3()
	{
		Random random = new Random();
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();

		for (int i = 0; i < 1; i++)
		{
			createRandomTransformationMatrix(matrix1, random);
			createRandomTransformationMatrix(matrix2, random);

			Transform3d transform1 = new Transform3d(matrix1);
			Transform3d transform2 = new Transform3d(matrix2);
			// matrix1.multiply(matrix2);
			matrix2.multiply(matrix1);
			transform1.multiply(transform2, transform1);

			Matrix4d transform1Mat4d = new Matrix4d();

			transform1.get(transform1Mat4d);

			assertMatrix4dEquals("", matrix2, transform1Mat4d, 1e-15);
		}
	}

	@Test
	public void TestOrthogonalityOfChainOfTransformations()
	{
		Random random = new Random();
		Matrix4d matrix1 = new Matrix4d();
		Transform3d transform = new Transform3d();

		for (int j = 0; j < nTests; j++)
		{
			for (int i = 0; i < 10000; i++)
			{
				createRandomTransformationMatrix(matrix1, random);
				Transform3d transformToMult = new Transform3d(matrix1);
				transform.multiply(transformToMult);
			}

			Matrix4d retXform = new Matrix4d();
			transform.get(retXform);

			RotationMatrixd mat = new RotationMatrixd();
			transform.getRotationScale(mat);
			assertTrue(mat.isRotationProper());
			assertTrue(checkOrthogonality(transform));
		}
	}

	@Test
	public void TestMatrixInverse()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);

			Transform3d transform3d = new Transform3d(denseMatrix);

			denseMatrix.invert();

			transform3d.invert();
			transform3d.get(checkMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestMatrixInverse7()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();
		Transform3d transform2 = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);

			Transform3d transform3d = new Transform3d(denseMatrix);
			transform2.invert(transform3d);
			transform3d.invert();
			denseMatrix.invert();

			transform3d.get(checkMatrix);
			transform2.get(denseMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestMatrixManyMatrixInverses()
	{
		Matrix4d matrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		RotationMatrixd mat = new RotationMatrixd();
		Random random = new Random();
		int nInverses = 100;
		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			Transform3d transform3d = new Transform3d(matrix);

			for (int j = 0; j < nInverses; j++)
			{
				transform3d.invert();
				matrix.invert();
			}
			transform3d.get(checkMatrix);
			transform3d.getRotationScale(mat);
			assertTrue(mat.isRotationProper());

			assertMatrix4dEquals("", matrix, checkMatrix, 1e-10);
		}
	}

	@Test
	public void TestNearSingularMatrixInverse()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);

			for (int j = 0; j < 3; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					denseMatrix.set(j, k, 1e-4 * random.nextDouble());
				}
			}

			Transform3d transform3d = new Transform3d(denseMatrix);

			denseMatrix.invert();

			transform3d.invert();
			transform3d.get(checkMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-3);
		}
	}

	@Test
	public void TestInvertTransform2()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix4d matrix2 = new Matrix4d();
		Vector3d vector = new Vector3d();
		Matrix4d checkMatrix2 = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);

			Vector3d scale = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			Transform3d transform = new Transform3d(matrix, vector, scale);
			transform.get(matrix2);
			matrix2.invert();
			Transform3d transform2 = new Transform3d(matrix2);

			transform2.invert();

			transform.get(checkMatrix);
			transform2.get(checkMatrix2);

			assertMatrix4dEquals("",checkMatrix2, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestInvertTransform3()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix4d matrix2 = new Matrix4d();
		Vector3d vector = new Vector3d();
		Matrix4d checkMatrix2 = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			randomizeVector(random, vector);

			Vector3d scale = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			Transform3d transform = new Transform3d(matrix, vector, scale);
			transform.invert();
			transform.get(matrix2);
			Transform3d transform2 = new Transform3d(matrix2);

			transform.get(checkMatrix);
			transform2.get(checkMatrix2);

			assertMatrix4dEquals("",checkMatrix2, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestMatrixInverseWithScale()
	{
		Matrix4d matrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();
		Vector3d checkScale = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			double scale = random.nextDouble();
			Transform3d transform3d = new Transform3d(matrix);
			transform3d.setScale(scale);

			matrix.multiply(scale);
			matrix.m33 = matrix.m33 / scale;
			matrix.m03 = matrix.m03 / scale;
			matrix.m13 = matrix.m13 / scale;
			matrix.m23 = matrix.m23 / scale;
			matrix.invert();

			transform3d.invert();
			transform3d.get(checkMatrix);
			transform3d.getScale(checkScale);

			assertMatrix4dEquals("", matrix, checkMatrix, 1e-6);
			assertEquals(1 / scale, checkScale.x, 1e-6);
			assertEquals(1 / scale, checkScale.y, 1e-6);
			assertEquals(1 / scale, checkScale.z, 1e-6);
		}
	}

	@Test
	public void TestMatrixInverseWithScale3()
	{
		Matrix4d matrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			double scalex = random.nextDouble();
			double scaley = random.nextDouble();
			double scalez = random.nextDouble();
			Transform3d transform3d = new Transform3d(matrix);
			transform3d.setScale(scalex, scaley, scalez);

			matrix.m00 = matrix.m00 * scalex;
			matrix.m10 = matrix.m10 * scalex;
			matrix.m20 = matrix.m20 * scalex;
			matrix.m01 = matrix.m01 * scaley;
			matrix.m11 = matrix.m11 * scaley;
			matrix.m21 = matrix.m21 * scaley;
			matrix.m02 = matrix.m02 * scalez;
			matrix.m12 = matrix.m12 * scalez;
			matrix.m22 = matrix.m22 * scalez;
			matrix.invert();
			matrix.invert();

			transform3d.invert();
			transform3d.invert();
			transform3d.get(checkMatrix);

			assertMatrix4dEquals("", matrix, checkMatrix, 1e-6);
		}
	}

	@Test
	public void TestMatrixInverseWithScale4()
	{
		Matrix4d matrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();
		Transform3d transform2 = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			double scalex = random.nextDouble();
			double scaley = random.nextDouble();
			double scalez = random.nextDouble();

			matrix.m00 = matrix.m00 * scalex;
			matrix.m10 = matrix.m10 * scalex;
			matrix.m20 = matrix.m20 * scalex;
			matrix.m01 = matrix.m01 * scaley;
			matrix.m11 = matrix.m11 * scaley;
			matrix.m21 = matrix.m21 * scaley;
			matrix.m02 = matrix.m02 * scalez;
			matrix.m12 = matrix.m12 * scalez;
			matrix.m22 = matrix.m22 * scalez;
			Transform3d transform3d = new Transform3d(matrix);
			matrix.invert();
			transform2.setIdentity();
			transform2.invert(transform3d);

			transform2.get(checkMatrix);

			assertMatrix4dEquals("", matrix, checkMatrix, 1e-6);
		}
	}

	@Test
	public void TestMatrixInverseWithScale2()
	{
		Matrix4d matrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Random random = new Random();
		Vector3d checkScale = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);

			Vector3d scale = new Vector3d(random.nextDouble(),
					random.nextDouble(), random.nextDouble());
			Transform3d transform3d = new Transform3d(matrix);
			transform3d.setScale(scale);

			matrix.m00 *= scale.x;
			matrix.m10 *= scale.x;
			matrix.m20 *= scale.x;
			matrix.m01 *= scale.y;
			matrix.m11 *= scale.y;
			matrix.m21 *= scale.y;
			matrix.m02 *= scale.z;
			matrix.m12 *= scale.z;
			matrix.m22 *= scale.z;

			matrix.invert();

			transform3d.invert();
			transform3d.get(checkMatrix);
			transform3d.getScale(checkScale);

			Vector3d checkScaleX = new Vector3d(matrix.m00, matrix.m10,
					matrix.m20);
			Vector3d checkScaleY = new Vector3d(matrix.m01, matrix.m11,
					matrix.m21);
			Vector3d checkScaleZ = new Vector3d(matrix.m02, matrix.m12,
					matrix.m22);
			double xScale = checkScaleX.length();
			double yScale = checkScaleY.length();
			double zScale = checkScaleZ.length();

			assertMatrix4dEquals("", matrix, checkMatrix, 1e-6);
			assertEquals(xScale, checkScale.x, 1e-6);
			assertEquals(yScale, checkScale.y, 1e-6);
			assertEquals(zScale, checkScale.z, 1e-6);
		}
	}

	@Test
	public void TestMatrixInverse2()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Matrix4d matrix = new Matrix4d();
		Random random = new Random();
		Transform3d transform2 = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);
			createRandomTransformationMatrix(matrix, random);

			Transform3d transform3d = new Transform3d(matrix);
			transform2.invert(transform3d);

			transform2.invert();

			matrix.invert();
			transform2.get(denseMatrix);
			transform3d.get(checkMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}
	
	@Test
	public void TestMatrixInverse8()
	{
		Matrix4d denseMatrix = new Matrix4d();
		Matrix4d checkMatrix = new Matrix4d();
		Matrix4d matrix = new Matrix4d();
		Random random = new Random();
		Transform3d transform2 = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(denseMatrix, random);
			createRandomTransformationMatrix(matrix, random);

			Transform3d transform3d = new Transform3d(matrix);
			transform2.invert(transform3d);

			transform3d.invert(transform3d);

			matrix.invert();
			transform2.get(denseMatrix);
			transform3d.get(checkMatrix);

			assertMatrix4dEquals("",denseMatrix, checkMatrix, 1e-15);
		}
	}

	@Test
	public void TestEpsilonEquals()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		double epsilon = 1e-8;

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			Transform3d transform1 = new Transform3d(matrix);
			Transform3d transform2 = new Transform3d(matrix);

			assertTrue(transform1.epsilonEquals(transform2, epsilon));
			assertTrue(transform2.epsilonEquals(transform1, epsilon));

			for (int row = 0; row < 4; row++)
			{
				for (int col = 0; col < 4; col++)
				{
					// Change one element at a time.
					matrix.set(row, col, 2 + 10 * random.nextDouble());
					transform1.set(matrix);
					assertFalse(transform1.epsilonEquals(transform2, epsilon));
					transform1.set(transform2);
				}
			}
		}
	}

	@Test
	public void TestEpsilonEqualsWithScale()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		double epsilon = 1e-8;

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			Transform3d transform1 = new Transform3d(matrix);
			Transform3d transform2 = new Transform3d(matrix);
			double scale = random.nextDouble();
			transform1.setScale(scale);
			transform2.setScale(scale);

			assertTrue(transform1.epsilonEquals(transform2, epsilon));
			assertTrue(transform2.epsilonEquals(transform1, epsilon));

			for (int row = 0; row < 4; row++)
			{
				for (int col = 0; col < 4; col++)
				{
					// Change one element at a time.
					matrix.set(row, col, 2 + 10 * random.nextDouble());
					transform1.set(matrix);
					assertFalse(transform1.epsilonEquals(transform2, epsilon));
					transform1.set(transform2);
				}
			}
		}
	}

	@Test
	public void TestRotX()
	{
		Vector3d vector = new Vector3d(1, 0, 0);
		Vector3d vector2 = new Vector3d(0, 1, 0);
		Vector3d vector3 = new Vector3d(0, 0, 1);
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Transform3d transform = new Transform3d();
		transform.rotX(Math.PI / 2);
		transform.getRotation(rotationMatrix);

		rotationMatrix.rotate(vector);
		rotationMatrix.rotate(vector2);
		rotationMatrix.rotate(vector3);
		
		assertVector3dEquals("", new Vector3d(1, 0, 0), vector,
				1e-12);
		assertVector3dEquals("", new Vector3d(0, 0, 1), vector2,
				1e-12);
		assertVector3dEquals("", new Vector3d(0, -1, 0), vector3,
				1e-12);
	}

	@Test
	public void TestRotY()
	{
		Vector3d vector = new Vector3d(1, 0, 0);
		Vector3d vector2 = new Vector3d(0, 1, 0);
		Vector3d vector3 = new Vector3d(0, 0, 1);
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Transform3d transform = new Transform3d();
		transform.rotY(Math.PI / 2);
		transform.getRotation(rotationMatrix);

		rotationMatrix.rotate(vector);
		rotationMatrix.rotate(vector2);
		rotationMatrix.rotate(vector3);

		assertVector3dEquals("", new Vector3d(0, 0, -1), vector,
				1e-12);
		assertVector3dEquals("", new Vector3d(0, 1, 0), vector2,
				1e-12);
		assertVector3dEquals("", new Vector3d(1, 0, 0), vector3,
				1e-12);
	}

	@Test
	public void TestRotZ()
	{
		Vector3d vector = new Vector3d(1, 0, 0);
		Vector3d vector2 = new Vector3d(0, 1, 0);
		Vector3d vector3 = new Vector3d(0, 0, 1);
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Transform3d transform = new Transform3d();
		transform.rotZ(Math.PI / 2);
		transform.getRotation(rotationMatrix);

		rotationMatrix.rotate(vector);
		rotationMatrix.rotate(vector2);
		rotationMatrix.rotate(vector3);

		assertVector3dEquals("", new Vector3d(0, 1, 0), vector,
				1e-12);
		assertVector3dEquals("", new Vector3d(-1, 0, 0), vector2,
				1e-12);
		assertVector3dEquals("", new Vector3d(0, 0, 1), vector3,
				1e-12);
	}

	@Test
	public void TestEquals()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			Transform3d transform1 = new Transform3d(matrix);
			Transform3d transform2 = new Transform3d(matrix);

			assertTrue(transform1.equals(transform2));

			transform1.mat10 = transform1.mat10 + 1;
			assertFalse(transform1.equals(transform2));
		}
	}

	@Test
	public void TestEqualsWithScale()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			Transform3d transform1 = new Transform3d(matrix);
			Transform3d transform2 = new Transform3d(matrix);

			assertTrue(transform1.equals(transform2));

			transform1.mat10 = transform1.mat10 + 1;
			assertFalse(transform1.equals(transform2));
		}
	}

	@Test
	public void TestEulerAngles()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		Vector3d vectorToCheck = new Vector3d();
		Transform3d transform = new Transform3d();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = -Math.PI + random.nextDouble() * 2 * Math.PI;
			vector.y = -(Math.PI / 2 - 0.01) * random.nextDouble() - 0.01;
			vector.z = -Math.PI + random.nextDouble() * 2 * Math.PI;

			transform.setEuler(vector);

			transform.getEulerXYZ(vectorToCheck);
			assertVector3dEquals("", vector, vectorToCheck, 1e-5);
		}
	}

	@Test
	public void TestEulerAnglesWithScale()
	{
		Random random = new Random();
		Vector3d vector = new Vector3d();
		Vector3d vectorToCheck = new Vector3d();
		Transform3d transform = new Transform3d();
		Vector3d scaleCheck = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			vector.x = -Math.PI + random.nextDouble() * 2 * Math.PI;
			vector.y = -(Math.PI / 2 - 0.01) * random.nextDouble() - 0.01;
			vector.z = -Math.PI + random.nextDouble() * 2 * Math.PI;

			transform.setEuler(vector);
			double scale = random.nextDouble();
			transform.setScale(scale);

			transform.getScale(scaleCheck);
			transform.getEulerXYZ(vectorToCheck);
			assertVector3dEquals("", vector, vectorToCheck, 1e-5);
			assertEquals(scale, scaleCheck.x, 1e-6);
			assertEquals(scale, scaleCheck.y, 1e-6);
			assertEquals(scale, scaleCheck.z, 1e-6);
		}
	}

	@Test
	public void TestTransformVector4d()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		Vector4d vector = new Vector4d();
		Vector4d vector2 = new Vector4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);

			createRandomTransform4Vector(vector, random);
			vector2.set(vector);

			Vector4d V = multiplyMatrix4dByVector4d(matrix, vector);
			transform.transform(vector2);

			assertVector4dEquals("", V, vector2, 1e-12);
		}
	}

	@Test
	public void TestTransformVector4d2()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		Vector4d vector = new Vector4d();
		Vector4d vector2 = new Vector4d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);

			createRandomTransform4Vector(vector, random);

			transform.transform(vector, vector2);
			Vector4d V = multiplyMatrix4dByVector4d(matrix, vector);

			assertVector4dEquals("", V, vector2, 1e-12);
		}
	}

	@Test
	public void TestTransformVector4f()
	{
		Random random = new Random();
		Matrix4f matrix = new Matrix4f();
		Vector4f vector = new Vector4f();
		Vector4f vector2 = new Vector4f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);

			createRandomTransform4Vector(vector, random);
			vector2.set(vector);

			Vector4f V = multiplyMatrix4fByVector4f(matrix, vector);
			transform.transform(vector2);

			assertVector4fEquals("", V, vector2, 1e-6);
		}
	}

	@Test
	public void TestTransformVector4f2()
	{
		Random random = new Random();
		Matrix4f matrix = new Matrix4f();
		Vector4f vector = new Vector4f();
		Vector4f vector2 = new Vector4f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);

			createRandomTransform4Vector(vector, random);

			transform.transform(vector, vector2);
			Vector4f V = multiplyMatrix4fByVector4f(matrix, vector);

			assertVector4fEquals("", V, vector2, 1e-6);
		}
	}

	@Test
	public void TestTransformVector3d()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Vector3d vector = new Vector3d();
		Vector3d vector2 = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);

			randomizeVector(random, vector);
			vector2.set(vector);

			rotationMatrix.rotate(vector);
			transform.transform(vector2);

			assertVector3dEquals("", vector, vector2, 1e-12);
		}
	}

	@Test
	public void TestTransformVector3f()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixf rotationMatrix = new RotationMatrixf();
		Vector3f vector = new Vector3f();
		Vector3f vector2 = new Vector3f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);

			randomizeVector(random, vector);
			vector2.set(vector);

			rotationMatrix.rotate(vector);
			transform.transform(vector2);

			assertVector3fEquals("", vector, vector2, 1e-6);
		}
	}

	@Test
	public void TestTransformVector3f2()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixf rotationMatrix = new RotationMatrixf();
		Vector3f vector = new Vector3f();
		Vector3f vector2 = new Vector3f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);

			randomizeVector(random, vector);

			transform.transform(vector, vector2);
			rotationMatrix.rotate(vector);

			assertVector3fEquals("", vector, vector2, 1e-6);
		}
	}

	@Test
	public void TestTransformVector3d2()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Vector3d vector = new Vector3d();
		Vector3d vector2 = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);

			randomizeVector(random, vector);

			transform.transform(vector, vector2);
			rotationMatrix.rotate(vector);

			assertVector3dEquals("", vector, vector2, 1e-6);
		}
	}

	@Test
	public void TestTransformPoint3d()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Point3d point = new Point3d();
		Point3d point2 = new Point3d();
		Vector3d vector = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);
			transform.getTranslation(vector);

			randomizePoint3d(random, point);
			point2.set(point);

			rotationMatrix.rotate(point);
			point.add(vector);
			transform.transform(point2);

			assertPoint3dEquals("", point, point2, 1e-12);
		}
	}

	@Test
	public void TestTransformPoint3d2()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixd rotationMatrix = new RotationMatrixd();
		Point3d point = new Point3d();
		Point3d point2 = new Point3d();
		Vector3d vector = new Vector3d();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);
			transform.getTranslation(vector);

			randomizePoint3d(random, point);

			transform.transform(point, point2);
			rotationMatrix.rotate(point);
			point.add(vector);

			assertPoint3dEquals("", point, point2, 1e-12);
		}
	}

	@Test
	public void TestTransformPoint3f()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixf rotationMatrix = new RotationMatrixf();
		Point3f point = new Point3f();
		Point3f point2 = new Point3f();
		Vector3f vector = new Vector3f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);
			transform.getTranslation(vector);

			randomizePoint3f(random, point);
			point2.set(point);

			rotationMatrix.rotate(point);
			point.add(vector);
			transform.transform(point2);

			assertPoint3fEquals("", point, point2, 1e-6);
		}
	}

	@Test
	public void TestTransformPoint3f2()
	{
		Random random = new Random();
		Matrix4d matrix = new Matrix4d();
		RotationMatrixf rotationMatrix = new RotationMatrixf();
		Point3f point = new Point3f();
		Point3f point2 = new Point3f();
		Vector3f vector = new Vector3f();
		Transform3d transform = new Transform3d();

		for (int i = 0; i < nTests; i++)
		{
			createRandomTransformationMatrix(matrix, random);
			transform.set(matrix);
			transform.getRotation(rotationMatrix);
			transform.getTranslation(vector);

			randomizePoint3f(random, point);

			transform.transform(point, point2);
			rotationMatrix.rotate(point);
			point.add(vector);

			assertPoint3fEquals("", point, point2, 1e-6);
		}
	}

	private boolean checkOrthogonality(Transform3d transform)
	{
		Matrix3d matrix = new Matrix3d();
		transform.getRotation(matrix);

		Vector3d tmpVecX = new Vector3d(matrix.m00, matrix.m10, matrix.m20);
		Vector3d tmpVecY = new Vector3d(matrix.m01, matrix.m11, matrix.m21);
		Vector3d tmpVecZ = new Vector3d(matrix.m02, matrix.m12, matrix.m22);

		return (tmpVecX.lengthSquared() - 1 < 1e-8)
				&& (tmpVecY.lengthSquared() - 1 < 1e-8)
				&& (tmpVecZ.lengthSquared() - 1 < 1e-8);
	}

	private void randomizeVector(Random random, Vector3d vector)
	{
		vector.x = random.nextDouble();
		vector.y = random.nextDouble();
		vector.z = random.nextDouble();
	}

	private void randomizeVector(Random random, Vector3f vector)
	{
		vector.x = random.nextFloat();
		vector.y = random.nextFloat();
		vector.z = random.nextFloat();
	}

	private void randomizePoint3d(Random random, Point3d point)
	{
		point.x = random.nextDouble();
		point.y = random.nextDouble();
		point.z = random.nextDouble();
	}

	private void randomizePoint3f(Random random, Point3f point)
	{
		point.x = random.nextFloat();
		point.y = random.nextFloat();
		point.z = random.nextFloat();
	}

	private void createRandomRotationMatrix(Matrix3d matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = rotX.m00;
		matrix.m01 = rotX.m01;
		matrix.m02 = rotX.m02;
		matrix.m10 = rotX.m10;
		matrix.m11 = rotX.m11;
		matrix.m12 = rotX.m12;
		matrix.m20 = rotX.m20;
		matrix.m21 = rotX.m21;
		matrix.m22 = rotX.m22;
	}

	private void createRandomRotationMatrix(Matrix3f matrix, Random random)
	{
		Matrix3f rotX = new Matrix3f();
		Matrix3f rotY = new Matrix3f();
		Matrix3f rotZ = new Matrix3f();
		Vector3f trans = new Vector3f();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = rotX.m00;
		matrix.m01 = rotX.m01;
		matrix.m02 = rotX.m02;
		matrix.m10 = rotX.m10;
		matrix.m11 = rotX.m11;
		matrix.m12 = rotX.m12;
		matrix.m20 = rotX.m20;
		matrix.m21 = rotX.m21;
		matrix.m22 = rotX.m22;
	}

	private void createRandomTransformationMatrix(Matrix4d matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = rotX.m00;
		matrix.m01 = rotX.m01;
		matrix.m02 = rotX.m02;
		matrix.m03 = trans.x;
		matrix.m10 = rotX.m10;
		matrix.m11 = rotX.m11;
		matrix.m12 = rotX.m12;
		matrix.m13 = trans.y;
		matrix.m20 = rotX.m20;
		matrix.m21 = rotX.m21;
		matrix.m22 = rotX.m22;
		matrix.m23 = trans.z;
		matrix.m30 = 0;
		matrix.m31 = 0;
		matrix.m32 = 0;
		matrix.m33 = 1;
	}

	private void createRandomTransformationMatrix(Matrix4f matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = (float) rotX.m00;
		matrix.m01 = (float) rotX.m01;
		matrix.m02 = (float) rotX.m02;
		matrix.m03 = (float) trans.x;
		matrix.m10 = (float) rotX.m10;
		matrix.m11 = (float) rotX.m11;
		matrix.m12 = (float) rotX.m12;
		matrix.m13 = (float) trans.y;
		matrix.m20 = (float) rotX.m20;
		matrix.m21 = (float) rotX.m21;
		matrix.m22 = (float) rotX.m22;
		matrix.m23 = (float) trans.z;
		matrix.m30 = 0;
		matrix.m31 = 0;
		matrix.m32 = 0;
		matrix.m33 = 1;
	}

	private void createRandomRotationMatrixX(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = 1;
		matrix.m01 = 0;
		matrix.m02 = 0;
		matrix.m10 = 0;
		matrix.m11 = cTheta;
		matrix.m12 = -sTheta;
		matrix.m20 = 0;
		matrix.m21 = sTheta;
		matrix.m22 = cTheta;
	}

	private void createRandomRotationMatrixX(Random random, Matrix3f matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = 1;
		matrix.m01 = 0;
		matrix.m02 = 0;
		matrix.m10 = 0;
		matrix.m11 = (float) cTheta;
		matrix.m12 = (float) -sTheta;
		matrix.m20 = 0;
		matrix.m21 = (float) sTheta;
		matrix.m22 = (float) cTheta;
	}

	private void createRandomRotationMatrixY(Random random, Matrix3f matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = (float) cTheta;
		matrix.m01 = 0;
		matrix.m02 = (float) sTheta;
		matrix.m10 = 0;
		matrix.m11 = 1;
		matrix.m12 = 0;
		matrix.m20 = (float) -sTheta;
		matrix.m21 = 0;
		matrix.m22 = (float) cTheta;
	}

	private void createRandomRotationMatrixY(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = cTheta;
		matrix.m01 = 0;
		matrix.m02 = sTheta;
		matrix.m10 = 0;
		matrix.m11 = 1;
		matrix.m12 = 0;
		matrix.m20 = -sTheta;
		matrix.m21 = 0;
		matrix.m22 = cTheta;
	}

	private void createRandomRotationMatrixZ(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = cTheta;
		matrix.m01 = -sTheta;
		matrix.m02 = 0;
		matrix.m10 = sTheta;
		matrix.m11 = cTheta;
		matrix.m12 = 0;
		matrix.m20 = 0;
		matrix.m21 = 0;
		matrix.m22 = 1;
	}

	private void createRandomRotationMatrixZ(Random random, Matrix3f matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = (float) cTheta;
		matrix.m01 = (float) -sTheta;
		matrix.m02 = 0;
		matrix.m10 = (float) sTheta;
		matrix.m11 = (float) cTheta;
		matrix.m12 = 0;
		matrix.m20 = 0;
		matrix.m21 = 0;
		matrix.m22 = 1;
	}

	private void createRandomTransform4Vector(Vector4d vector, Random random)
	{
		vector.x = random.nextDouble();
		vector.y = random.nextDouble();
		vector.z = random.nextDouble();
		vector.w = 1;
	}

	private void createRandomTransform4Vector(Vector4f vector, Random random)
	{
		vector.x = random.nextFloat();
		vector.y = random.nextFloat();
		vector.z = random.nextFloat();
		vector.w = 1;
	}

	private float[] putDoublesInFloatArray(double[] matrixAsDoubleArray)
	{
		float[] ret = new float[matrixAsDoubleArray.length];
		for (int i = 0; i < matrixAsDoubleArray.length; i++)
		{
			ret[i] = (float) matrixAsDoubleArray[i];
		}

		return ret;
	}

	private void createFloatArrayFromMatrix4d(float[] floatArray,
			Matrix4f matrix)
	{
		floatArray[0] = matrix.m00;
		floatArray[1] = matrix.m01;
		floatArray[2] = matrix.m02;
		floatArray[3] = matrix.m03;
		floatArray[4] = matrix.m10;
		floatArray[5] = matrix.m11;
		floatArray[6] = matrix.m12;
		floatArray[7] = matrix.m13;
		floatArray[8] = matrix.m20;
		floatArray[9] = matrix.m21;
		floatArray[10] = matrix.m22;
		floatArray[11] = matrix.m23;
		floatArray[12] = matrix.m30;
		floatArray[13] = matrix.m31;
		floatArray[14] = matrix.m32;
		floatArray[15] = matrix.m33;
	}

	private void messWithRotationMatrixOrthogonality(Matrix4d matrix,
			Random random)
	{
		matrix.m00 += random.nextDouble() * 1e-3;
		matrix.m01 += random.nextDouble() * 1e-3;
		matrix.m02 += random.nextDouble() * 1e-3;
		matrix.m10 += random.nextDouble() * 1e-3;
		matrix.m11 += random.nextDouble() * 1e-3;
		matrix.m12 += random.nextDouble() * 1e-3;
		matrix.m20 += random.nextDouble() * 1e-3;
		matrix.m21 += random.nextDouble() * 1e-3;
		matrix.m22 += random.nextDouble() * 1e-3;
	}

	private void assertRotationMatrixdEquals(RotationMatrixd matrix1,
			RotationMatrixd matrix2, double epsilon)
	{
		assertEquals(matrix1.m00, matrix2.m00, epsilon);
		assertEquals(matrix1.m01, matrix2.m01, epsilon);
		assertEquals(matrix1.m02, matrix2.m02, epsilon);
		assertEquals(matrix1.m10, matrix2.m10, epsilon);
		assertEquals(matrix1.m11, matrix2.m11, epsilon);
		assertEquals(matrix1.m12, matrix2.m12, epsilon);
		assertEquals(matrix1.m20, matrix2.m20, epsilon);
		assertEquals(matrix1.m21, matrix2.m21, epsilon);
		assertEquals(matrix1.m22, matrix2.m22, epsilon);
	}

	private void assertVector3dEquals(String string, Vector3d vector1,
			Vector3d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	private void assertVector3fEquals(String string, Vector3f vector1,
			Vector3f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	private void assertPoint3dEquals(String string, Point3d vector1,
			Point3d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	private void assertPoint3fEquals(String string, Point3f vector1,
			Point3f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	private void assertVector4dEquals(String string, Vector4d vector1,
			Vector4d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
		assertEquals(vector1.w, vector2.w, epsilon);
	}

	private void assertVector4fEquals(String string, Vector4f vector1,
			Vector4f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
		assertEquals(vector1.w, vector2.w, epsilon);
	}

	private void assertMatrix4dEquals(String string, Matrix4d m1, Matrix4d m2,
			double epsilon)
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	private void assertMatrix4fEquals(String string, Matrix4f m1, Matrix4f m2,
			double epsilon)
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	private void assertMatrix3dEquals(String string, Matrix3d m1, Matrix3d m2,
			double epsilon)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	private void assertMatrix3fEquals(String string, Matrix3f m1, Matrix3f m2,
			double epsilon)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	private Vector4d multiplyMatrix4dByVector4d(Matrix4d M, Vector4d V)
	{
		Vector4d ret = new Vector4d();

		ret.x = V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03;
		ret.y = V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13;
		ret.z = V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23;
		ret.w = V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33;

		return ret;
	}

	private Vector4f multiplyMatrix4dByVector4f(Matrix4d M, Vector4f V)
	{
		Vector4f ret = new Vector4f();

		ret.x = (float) (V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03);
		ret.y = (float) (V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13);
		ret.z = (float) (V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23);
		ret.w = (float) (V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33);

		return ret;
	}

	private Vector4f multiplyMatrix4fByVector4f(Matrix4f M, Vector4f V)
	{
		Vector4f ret = new Vector4f();

		ret.x = (float) (V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03);
		ret.y = (float) (V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13);
		ret.z = (float) (V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23);
		ret.w = (float) (V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33);

		return ret;
	}
}
