package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSatuan is a Querydsl query type for Satuan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSatuan extends EntityPathBase<Satuan> {

    private static final long serialVersionUID = 107309805L;

    public static final QSatuan satuan = new QSatuan("satuan");

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

    public QSatuan(String variable) {
        super(Satuan.class, forVariable(variable));
    }

    public QSatuan(Path<? extends Satuan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSatuan(PathMetadata<?> metadata) {
        super(Satuan.class, metadata);
    }

}

