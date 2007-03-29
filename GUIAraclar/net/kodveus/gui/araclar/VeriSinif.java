/*
 * Emre tarafindan Jun 10, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.io.Serializable;


/**
 * @author Emre
 * Butun  veri siniflarimiz bu siniftan turetilmelidir
 */
public abstract class VeriSinif implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected AliasMap aliasMap;
    public String aciklama;

    public AliasMap getAliasMap() {
        aliasMap = new AliasMap(this.getClass());
        prepareMap();

        return aliasMap;
    }

    public abstract void prepareMap();

    /**
     * @return Returns the aciklama.
     */
    public java.lang.String getAciklama() {
        return this.aciklama;
    }

    /**
     * @param aciklama The aciklama to set.
     */
    public void setAciklama(java.lang.String aciklama) {
        this.aciklama = aciklama;
    }
}
