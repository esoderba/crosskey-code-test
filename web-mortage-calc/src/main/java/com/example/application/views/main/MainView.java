package com.example.application.views.main;

import com.example.application.mortagecalc.Prospect;
import com.example.application.mortagecalc.ProspectParser;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() throws IOException {
        RegexpValidator floatValidator = new RegexpValidator("/^\\d+\\.\\d$|^\\d+$/", "only two decimals allowed");
        ProspectParser parser = new ProspectParser();
        ArrayList<Prospect> prospects = new ArrayList<>(parser.parse(Paths.get("prospects.txt")));
        VerticalLayout prospectList = new VerticalLayout();
        AtomicInteger prospectNr = new AtomicInteger();
        for (Prospect prospect: prospects) {
            prospectNr.getAndIncrement();
            prospectList.add(new H4(prospect.toString(prospectNr.get())));
        }
        TextField nameField = new TextField("Name");
        NumberField totalField = new NumberField("Total Amount");
        IntegerField interestField = new IntegerField("Interest");
        IntegerField yearsField = new IntegerField("Years");
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            prospectNr.getAndIncrement();
            Prospect prospect = new Prospect(nameField.getValue(), totalField.getValue()*100, interestField.getValue()*100, yearsField.getValue());
            prospectList.add(new H4(prospect.toString(prospectNr.get())));
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Mortage calculator"),
                prospectList,
                new HorizontalLayout(
                        nameField,
                        totalField,
                        interestField,
                        yearsField
                ),
                addButton,
                new H3("Potential issues left in due to time limit: "),
                new H4("Fields currently lack validation and as such inputs needs to follow same format as the provided .txt file"),
                new H4("Data does not persist between sessions")
        );
    }
}