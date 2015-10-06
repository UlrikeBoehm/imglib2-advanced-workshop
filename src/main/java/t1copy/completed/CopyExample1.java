package t1copy.completed;

import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.RandomAccess;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.io.ImgIOException;
import net.imglib2.io.ImgOpener;
import net.imglib2.type.numeric.real.FloatType;

public class CopyExample1
{
	public static void copy( final Img< FloatType > source, final Img< FloatType > target )
	{
		final RandomAccess< FloatType > in = source.randomAccess();
		final Cursor< FloatType > out = target.localizingCursor();
		while ( out.hasNext() )
		{
			out.fwd();
			in.setPosition( out );
			out.get().set( in.get() );
		}
	}

	public static void main( final String[] args ) throws ImgIOException
	{
		// load input image
		final String filename = "images/bee-1.tif";
		final FloatType type = new FloatType();
		final ImgFactory< FloatType > factory = new ArrayImgFactory< FloatType >();
		final Img< FloatType > input = new ImgOpener().openImg( filename, factory, type );

		// create output image to hold a copy of the input image
		final Dimensions dim = input;
		final Img< FloatType > output = factory.create( dim, type );

		// copy input to output
		copy( input, output );

		// show output
		ImageJFunctions.show( output );
	}
}