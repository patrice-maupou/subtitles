/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.maupou.subtitles;

import org.openide.loaders.MultiDataObject;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.TopComponent;

/**
 *
 * @author Patrice
 */
public class subTitlesopenSupport extends OpenSupport {

    public subTitlesopenSupport(MultiDataObject.Entry entry) {
        super(entry);
    }

    @Override
    protected CloneableTopComponent createCloneableTopComponent() {
        MultiDataObject mdo = (MultiDataObject) entry.getDataObject();
        //TopComponent tc = new TopComponent(mdo);
        //tc.setDisplayName(mdo.getName());
        return null;
    }
}
