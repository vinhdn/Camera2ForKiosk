package com.example.android.camera2.basic;

import com.google.zxing.LuminanceSource;

final public class PlanarYUVLuminanceSource extends LuminanceSource {

    private final byte[] mYuvData;

    public PlanarYUVLuminanceSource(byte[] yuvData, int width, int height) {
        super(width, height);

        mYuvData = yuvData;
    }

    @Override
    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + y);
        }
        final int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        final int offset = y * width;
        System.arraycopy(mYuvData, offset, row, 0, width);
        return row;
    }

    @Override
    public byte[] getMatrix() {
        return mYuvData;
    }

    @Override
    public boolean isCropSupported() {
        return true;
    }
}
