package it.uniroma2.dicii.ispw.view;

import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.enumeration.IssueState;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.model.Issue;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.List;

public class SecretaryViewDetailController {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Text featureTextField;

    @FXML
    private Text classroomTextField;

    @FXML
    private ComboBox statusCombo;

    @FXML
    private Button confirmButton;

    @FXML
    private Button discardButton;

    @FXML
    private TableView<IssueBean> storyTableView;

    @FXML
    private TableColumn<IssueBean, String> storyTableStateColumn;

    @FXML
    private TableColumn<IssueBean, String> storyTableDataColumn;

    private ObservableList<IssueState> states = FXCollections.observableArrayList();

    private ObservableList<IssueBean> issueStates = FXCollections.observableArrayList();

    private IssueBean bean;

    @FXML
    private void initialize() {

        confirmButton.setOnAction(this::confirmButtonAction);
        discardButton.setOnAction(this::discardButtonAction);
    }

    private void discardButtonAction(ActionEvent actionEvent) {
        SceneManager.getSingletonInstance().showSecretaryView();
    }

    private void confirmButtonAction(ActionEvent actionEvent) {

        IssueManagementController controller = new IssueManagementController();

        IssueBean bean = new IssueBean();
        bean.setId(this.bean.getFeature().getId());
        bean.setDescription(descriptionTextArea.getText());
        bean.setState(IssueState.valueOf(statusCombo.getSelectionModel().getSelectedItem().toString()));

        try {

            controller.updateIssue(bean);
            SceneManager.getSingletonInstance().showSecretaryView();

        } catch (DaoException e) {
            e.printStackTrace();

            // Show allert!
            // TODO
        }
    }

    /**
     *
     * @param issueBean userBean
     */
    public void onCreateView(IssueBean issueBean) {

        this.bean = issueBean;

        descriptionTextArea.setText(issueBean.getDescription());
        featureTextField.setText(issueBean.getFeature().getName());
        classroomTextField.setText(issueBean.getClassroom().getName());

        IssueManagementController controller = new IssueManagementController();

        try {

            List<IssueState> list = controller.getStateList();
            states.addAll(list);
            statusCombo.setItems(states);

            List<IssueBean> listBean = controller.getStateListForIssue(issueBean);
            issueStates.addAll(listBean);
            storyTableView.setItems(issueStates);

        } catch (DaoException e) {
            e.printStackTrace();
            // todo no data available
        }

        storyTableStateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));
        storyTableDataColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
    }

}
