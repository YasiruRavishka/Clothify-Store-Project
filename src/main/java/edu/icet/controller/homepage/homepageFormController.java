package edu.icet.controller.homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.service.BoFactory;
import edu.icet.service.custom.EmployeeBo;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import edu.icet.dto.Employee;
import edu.icet.util.ProductSize;
import edu.icet.dto.Supplier;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class homepageFormController implements Initializable {
    private Stage cartPage;

    @FXML
    private JFXButton btnEscapeEmployee;

    @FXML
    private JFXButton btnEscapeProduct;

    @FXML
    private JFXButton btnEscapeSupplier;

    @FXML
    private JFXButton btnSubmitEmployee;

    @FXML
    private JFXButton btnSubmitProduct;

    @FXML
    private JFXButton btnSubmitSupplier;

    @FXML
    private TableColumn<?, ?> colEmployeeAddress;

    @FXML
    private TableColumn<?, ?> colEmployeeEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colSupplierEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private Label lblEmployeeTitle;

    @FXML
    private Label lblProductTitle;

    @FXML
    private Label lblSupplierTitle;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private Spinner<Integer> txtProductQtyOnHand;

    @FXML
    private JFXComboBox<ProductSize> cmbProductSize;

    @FXML
    private JFXTextField txtProductUnitPrice;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ProductSize> productSizes = FXCollections.observableArrayList();
        productSizes.addAll(Arrays.stream(ProductSize.values()).toList());
        cmbProductSize.setItems(productSizes);
        cmbProductSize.setValue(ProductSize.NONE);
    }

    @FXML
    void btnAddNewEmployeeOnAction(ActionEvent event) {
        lblEmployeeTitle.setText("New Employee");
        txtEmployeeName.setText(null);
        txtEmployeeAddress.setText(null);
        txtEmployeeEmail.setText(null);
        btnSubmitEmployee.setText("Add");
        btnEscapeEmployee.setText("Clear");
    }

    @FXML
    void btnAddNewSupplierOnAction(ActionEvent event) {
        lblSupplierTitle.setText("New Supplier");
        txtSupplierName.setText(null);
        txtSupplierEmail.setText(null);
        btnSubmitSupplier.setText("Add");
        btnEscapeSupplier.setText("Clear");
    }

    @FXML
    void btnCartPageOnAction(ActionEvent event) {
        if (null==cartPage){
            try {
                cartPage = new Stage();
                cartPage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cartPage.fxml"))));
                cartPage.getIcons().add(new Image("/img/cart-dark.png"));
                cartPage.setTitle("Cart Page");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        cartPage.requestFocus();
        cartPage.show();
    }

    @FXML
    void btnEscapeEmployeeOnAction(ActionEvent event) {
        if (btnEscapeEmployee.getText().equals("Clear") && btnSubmitEmployee.getText().equals("Add")){
            txtEmployeeName.setText(null);
            txtEmployeeAddress.setText(null);
            txtEmployeeEmail.setText(null);
            return;
        }
        if (btnEscapeEmployee.getText().equals("Delete") && btnSubmitEmployee.getText().equals("Update")){

        }
    }

    @FXML
    void btnEscapeProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnEscapeSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnPreviousOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnSubmitEmployeeOnAction(ActionEvent event) {
        String employeeName = txtEmployeeName.getText();
        String employeeAddress = txtEmployeeAddress.getText();
        String employeeEmail = txtEmployeeEmail.getText();
        if (employeeName.isBlank() || employeeName.isEmpty() || employeeAddress.isBlank() || employeeAddress.isEmpty() || employeeEmail.isBlank() || employeeEmail.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Empty field founded!").show();
            return;
        }
        Employee newEmployee = new Employee(null,employeeName,employeeAddress,employeeEmail);
        EmployeeBo employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        if(employeeService.addEmployee(newEmployee)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added !").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !").show();
        }
    }

    @FXML
    void btnSubmitProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnSubmitSupplierOnAction(ActionEvent event) {

    }

    private boolean isEmptyOrBlank(){
        switch (tabPane.getSelectionModel().getSelectedItem().getText()){
            case ("Order"):
                break;
        }
        return false;
    }

}
