package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPelanggan is a Querydsl query type for Pelanggan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPelanggan extends EntityPathBase<Pelanggan> {

    private static final long serialVersionUID = 1848656832L;

    public static final QPelanggan pelanggan = new QPelanggan("pelanggan");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath alamat = createString("alamat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath kode = createString("kode");

    public final StringPath kontak = createString("kontak");

    public final StringPath nama = createString("nama");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPelanggan(String variable) {
        super(Pelanggan.class, forVariable(variable));
    }

    public QPelanggan(Path<? extends Pelanggan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPelanggan(PathMetadata<?> metadata) {
        super(Pelanggan.class, metadata);
    }

}

