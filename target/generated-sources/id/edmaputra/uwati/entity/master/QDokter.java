package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDokter is a Querydsl query type for Dokter
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDokter extends EntityPathBase<Dokter> {

    private static final long serialVersionUID = -309467118L;

    public static final QDokter dokter = new QDokter("dokter");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath alamat = createString("alamat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nama = createString("nama");

    public final StringPath sip = createString("sip");

    public final StringPath spesialis = createString("spesialis");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QDokter(String variable) {
        super(Dokter.class, forVariable(variable));
    }

    public QDokter(Path<? extends Dokter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDokter(PathMetadata<?> metadata) {
        super(Dokter.class, metadata);
    }

}

