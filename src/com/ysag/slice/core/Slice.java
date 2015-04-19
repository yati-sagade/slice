import android.graphics.*;


public class Slice {
    private long mId;
    private String mName;
    private String mPath;
    private Drawable mDrawable;

    public Slice(long id, String name, String path, Drawable drawable) {
        mId = id;
        mName = name;
        mPath = path;
        mDrawable = drawable;
    }

    public Slice(String name, String path, Drawable drawable) {
        this(-1, name, path, drawable);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public Drawable getDrawable() {
        returm mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}
