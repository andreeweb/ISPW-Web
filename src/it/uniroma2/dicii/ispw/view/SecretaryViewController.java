package it.uniroma2.dicii.ispw.view;

import it.uniroma2.dicii.ispw.bean.IssueBean;
import it.uniroma2.dicii.ispw.bean.UserBean;
import it.uniroma2.dicii.ispw.controller.IssueManagementController;
import it.uniroma2.dicii.ispw.exception.DaoException;
import it.uniroma2.dicii.ispw.model.Issue;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * JavaFX (controller) class for SecretaryView.fxml
 *
 * @author Andrea Cerra
 */

public class SecretaryViewController {

    @FXML
    private TableView<IssueBean> issueTableView;

    @FXML
    private TableColumn<IssueBean, String> idColumn;

    @FXML
    private TableColumn<IssueBean, String> featureColumn;

    @FXML
    private TableColumn<IssueBean, String> descriptionColumn;

    @FXML
    private TableColumn<IssueBean, String> stateColumn;

    @FXML
    private TableColumn<IssueBean, String> classroomColumn;

    private ObservableList<IssueBean> issueData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Listen for selection changes and show the person details when changed.
        issueTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showIssueDetails(newValue));
    }

    /**
     *
     */
    public void onCreateView() {

        IssueManagementController controller = new IssueManagementController();

        try {

            List<IssueBean> list = controller.getIssueBeanList();
            issueData.addAll(list);
            issueTableView.setItems(issueData);

        } catch (DaoException e) {
            e.printStackTrace();
            // todo no data available
        }

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        featureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeature().getName()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));
        classroomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassroom().getName()));

    }

    /**
     *
     * @param issueBean
     */
    public void showIssueDetails(IssueBean issueBean){

        SceneManager.getSingletonInstance().showSecretaryDetailView(issueBean);
    }
}
