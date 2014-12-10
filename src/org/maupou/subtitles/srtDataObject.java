/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.maupou.subtitles;

import java.io.IOException;
import java.util.List;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_srt_LOADER=Files of srt"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_srt_LOADER",
        mimeType = "text/x-srt",
        extension = {"srt"})
@DataObject.Registration(
        mimeType = "text/x-srt",
        iconBase = "org/maupou/subtitles/application-text.png",
        displayName = "#LBL_srt_LOADER",
        position = 300)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300),
    @ActionReference(
            path = "Loaders/text/x-srt/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400)
})
public class srtDataObject extends MultiDataObject {

    private final Object[][] tabEntries;

    public Object[][] getTabEntries() {
        return tabEntries;
    }

    private enum status {

        range, times, text, empty
    };

    public srtDataObject(FileObject pf, MultiFileLoader loader)
            throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-srt", true);
        List<String> lines = pf.asLines();
        ArraySubTitles ast;
        if (lines.get(0).startsWith("{")) {
            ast = new subSubTitles();
        } else {
            ast = new srtSubTitles();
        }
        tabEntries = ast.ArrayEntries(lines);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_srt_EDITOR",
            iconBase = "org/maupou/subtitles/application-text.png",
            mimeType = "text/x-srt",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "srt",
            position = 1000)
    @Messages("LBL_srt_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }
}
