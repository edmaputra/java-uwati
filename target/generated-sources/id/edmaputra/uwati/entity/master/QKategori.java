package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QKategori is a Querydsl query type for Kategori
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKategori extends EntityPathBase<Kategori> {

    private static final long serialVersionUID = -1026666281L;

    public static final QKategori kategori = new QKategori("kategori");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath nama = createString("nama");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QKategori(String variable) {
        super(Kategori.class, forVariable(variable));
    }

    public QKategori(Path<? extends Kategori> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKategori(PathMetadata<?> metadata) {
        super(Kategori.class, metadata);
    }

}

