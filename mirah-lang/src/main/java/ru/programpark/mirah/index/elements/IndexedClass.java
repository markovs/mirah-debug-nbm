package ru.programpark.mirah.index.elements;

import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.parsing.spi.indexing.support.IndexResult;

public final class IndexedClass extends IndexedElement implements ClassElement {

    /** This class is a module rather than a proper class */
    public static final int MODULE = 1 << 6;

    private final String simpleName;

    protected IndexedClass(IndexResult result, String fqn, String simpleName, String attributes, int flags) {
        super(result, fqn, attributes, flags);
        this.simpleName = simpleName;
    }

    public static IndexedClass create(String simpleName, String fqn, IndexResult result,
        String attributes, int flags) {
        IndexedClass c = new IndexedClass(result, fqn, simpleName, attributes, flags);
        return c;
    }

    // XXX Is this necessary?
    @Override
    public String getSignature() {
        return in;
    }

    @Override
    public String getName() {
        return simpleName;
    }

    @Override
    public ElementKind getKind() {
        return (flags & MODULE) != 0 ? ElementKind.MODULE : ElementKind.CLASS;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IndexedClass && in != null) {
            return in.equals(((IndexedClass) o).in);
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return in == null ? super.hashCode() : in.hashCode();
    }

    @Override
    public String getFqn() {
        return in;
    }
}
