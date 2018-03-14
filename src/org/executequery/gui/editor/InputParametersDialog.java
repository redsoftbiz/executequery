package org.executequery.gui.editor;

import com.github.lgooddatepicker.components.DatePicker;
import org.executequery.components.BottomButtonPanel;
import org.executequery.gui.BaseDialog;
import org.executequery.gui.editor.autocomplete.Parameter;
import org.underworldlabs.swing.EQDateTimePicker;
import org.underworldlabs.swing.EQTimePicker;
import org.underworldlabs.swing.RDBCheckBox;
import org.underworldlabs.swing.RDBFieldFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class InputParametersDialog extends BaseDialog {

    private List<Parameter> parameters;
    private JScrollPane scrollPanel;
    private JPanel mainPanel;
    private JPanel panel;
    private List<JComponent> componentList;
    private List<JCheckBox> nullBoxes;

    public InputParametersDialog(List<Parameter> parameters) {
        super("Input Parameters", true, true);
        this.parameters = parameters;
        init();
    }

    private void init() {
        mainPanel = new JPanel();
        scrollPanel = new JScrollPane();
        panel = new JPanel();
        scrollPanel.setViewportView(panel);
        panel.setLayout(new GridBagLayout());
        componentList = new ArrayList<>();
        nullBoxes = new ArrayList<>();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(scrollPanel, new GridBagConstraints(0, 0,
                1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5),
                0, 0));
        // empty panel for stretch
        mainPanel.add(new JPanel(), new GridBagConstraints(0, 1, 2, 1,
                1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        BottomButtonPanel bottomButtonPanel = new BottomButtonPanel(this.isDialog());
        bottomButtonPanel.setOkButtonAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok();
            }
        });
        bottomButtonPanel.setOkButtonText("OK");
        mainPanel.add(bottomButtonPanel, new GridBagConstraints(0, 2,
                1, 1, 1, 0,
                GridBagConstraints.SOUTHEAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5),
                0, 0));
        for (int i = 0; i < parameters.size(); i++)
            addParameter(parameters.get(i));
        addDisplayComponent(mainPanel);
    }

    private void addParameter(Parameter parameter) {
        int count = componentList.size();
        panel.add(new JLabel(parameter.getName()), new GridBagConstraints(0, count,
                1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5),
                0, 0));
        panel.add(new JLabel(parameter.getTypeName()), new GridBagConstraints(1, count,
                1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5),
                0, 0));
        JComponent component;
        switch (parameter.getType()) {
            case Types.DATE:
                component = new DatePicker();
                break;
            case Types.TIMESTAMP:
                component = new EQDateTimePicker();
                break;
            case Types.TIME:
                component = new EQTimePicker();
                break;
            case Types.BOOLEAN:
                component = new RDBCheckBox();
                break;
            case Types.BINARY:
            case Types.BLOB:
            case Types.LONGVARBINARY:
                component = new RDBFieldFileChooser();
                break;
            default:
                component = new JTextField(14);
                break;
        }
        componentList.add(component);
        panel.add(component, new GridBagConstraints(2, count,
                1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5),
                0, 0));
    }

    private void ok() {
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            JComponent component = componentList.get(i);
            switch (parameter.getType()) {
                case Types.DATE:
                    parameter.setValue(((DatePicker) component).getDateStringOrEmptyString());
                    break;
                case Types.TIMESTAMP:
                    parameter.setValue(((EQDateTimePicker) component).getStringValue());
                    break;
                case Types.TIME:
                    parameter.setValue(((EQTimePicker) component).getStringValue());
                    break;
                case Types.BOOLEAN:
                    parameter.setValue(((RDBCheckBox) component).getStringValue());
                    break;
                case Types.BINARY:
                case Types.BLOB:
                case Types.LONGVARBINARY:
                    File file = ((RDBFieldFileChooser) component).getFile();
                    if (file != null) {
                        try {
                            parameter.setValue(new FileInputStream(file));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else parameter.setValue(null);
                    break;
                default:
                    parameter.setValue(((JTextField) component).getText());
                    break;
            }
        }
        finished();
    }
}