package id.edmaputra.uwati.entity.pasien;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRekamMedis is a Querydsl query type for RekamMedis
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRekamMedis extends EntityPathBase<RekamMedis> {

    private static final long serialVersionUID = -199939339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRekamMedis rekamMedis = new QRekamMedis("rekamMedis");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath anamnesa = createString("anamnesa");

    public final StringPath diagnosa = createString("diagnosa");

    public final id.edmaputra.uwati.entity.master.QKaryawan dokter;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final BooleanPath isMasukListResep = createBoolean("isMasukListResep");

    public final BooleanPath isResepSudahDiproses = createBoolean("isResepSudahDiproses");

    public final BooleanPath isSudahDisimpan = createBoolean("isSudahDisimpan");

    public final NumberPath<Integer> kunjungan = createNumber("kunjungan", Integer.class);

    public final StringPath nomor = createString("nomor");

    public final QPasien pasien;

    public final StringPath pemeriksaan = createString("pemeriksaan");

    public final ListPath<RekamMedisDetail, QRekamMedisDetail> rekamMedisDetail = this.<RekamMedisDetail, QRekamMedisDetail>createList("rekamMedisDetail", RekamMedisDetail.class, QRekamMedisDetail.class, PathInits.DIRECT2);

    public final ListPath<RekamMedisDiagnosa, QRekamMedisDiagnosa> rekamMedisDiagnosa = this.<RekamMedisDiagnosa, QRekamMedisDiagnosa>createList("rekamMedisDiagnosa", RekamMedisDiagnosa.class, QRekamMedisDiagnosa.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> tanggal = createDateTime("tanggal", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QRekamMedis(String variable) {
        this(RekamMedis.class, forVariable(variable), INITS);
    }

    public QRekamMedis(Path<? extends RekamMedis> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRekamMedis(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRekamMedis(PathMetadata<?> metadata, PathInits inits) {
        this(RekamMedis.class, metadata, inits);
    }

    public QRekamMedis(Class<? extends RekamMedis> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dokter = inits.isInitialized("dokter") ? new id.edmaputra.uwati.entity.master.QKaryawan(forProperty("dokter"), inits.get("dokter")) : null;
        this.pasien = inits.isInitialized("pasien") ? new QPasien(forProperty("pasien"), inits.get("pasien")) : null;
    }

}

