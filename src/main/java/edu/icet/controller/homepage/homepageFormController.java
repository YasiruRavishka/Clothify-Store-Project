package edu.icet.controller.homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.Product;
import edu.icet.service.BoFactory;
import edu.icet.service.custom.EmployeeBo;
import edu.icet.service.custom.ProductBo;
import edu.icet.service.custom.SupplierBo;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.icet.dto.Employee;
import edu.icet.util.ProductSize;
import edu.icet.dto.Supplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class homepageFormController implements Initializable {
    private Stage cartPage;
    private SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory;

    @FXML
    private BorderPane homepage;

    @FXML
    private JFXButton btnEscapeEmployee;

    @FXML
    private JFXButton btnEscapeProduct;

    @FXML
    private JFXButton btnEscapeSupplier;

    @FXML
    private JFXButton btnSelectImg;

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
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colProductQtyOnHand;

    @FXML
    private TableColumn<?, ?> colProductSize;

    @FXML
    private TableColumn<?, ?> colProductUnitPrice;

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
    private ImageView productImg;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableView<Product> tblProduct;

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
    private Spinner<Integer> spinnerProductQtyOnHand;

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
        //--------------------  Product  --------------------
        ObservableList<ProductSize> productSizes = FXCollections.observableArrayList();
        productSizes.addAll(Arrays.stream(ProductSize.values()).toList());
        cmbProductSize.setItems(productSizes);
        cmbProductSize.setValue(ProductSize.NONE);
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999);
        spinnerProductQtyOnHand.setValueFactory(spinnerValueFactory);
        //--------------------
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colProductUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colProductQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                ProductBo productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
                Product product = productService.searchProductById(newValue.getId());
                productImg.setImage(new Image(product.getImgSrc()));
                lblProductTitle.setText(String.format("%05d",product.getId()));
                txtProductName.setText(product.getName());
                cmbProductSize.setValue(product.getSize());
                txtProductUnitPrice.setText(String.format("%.2f",product.getUnitPrice()));
                spinnerValueFactory.setValue(product.getQtyOnHand());
                spinnerProductQtyOnHand.setValueFactory(spinnerValueFactory);
                btnSubmitProduct.setText("Update");
                btnEscapeProduct.setText("Delete");
            }
        });
        loadProductTable();
        //--------------------  Supplier  --------------------
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                lblSupplierTitle.setText(String.format("%05d",newValue.getId()));
                txtSupplierName.setText(newValue.getName());
                txtSupplierEmail.setText(newValue.getEmail());
                btnSubmitSupplier.setText("Update");
                btnEscapeSupplier.setText("Delete");
            }
        });
        loadSupplierTable();
        //--------------------  Employee  --------------------
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                lblEmployeeTitle.setText(String.format("%05d",newValue.getId()));
                txtEmployeeName.setText(newValue.getName());
                txtEmployeeAddress.setText(newValue.getAddress());
                txtEmployeeEmail.setText(newValue.getEmail());
                btnSubmitEmployee.setText("Update");
                btnEscapeEmployee.setText("Delete");
            }
        });
        loadEmployeeTable();
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
            clearAll();
            return;
        }
        if (btnEscapeEmployee.getText().equals("Delete") && btnSubmitEmployee.getText().equals("Update")){
            String employeeName = txtEmployeeName.getText();
            String employeeAddress = txtEmployeeAddress.getText();
            String employeeEmail = txtEmployeeEmail.getText();
            if (isNullOrEmptyOrBlank(employeeName, employeeAddress, employeeEmail)){
                new Alert(Alert.AlertType.WARNING,"Empty field founded!").show();
                return;
            }
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you want delete?");
            alertConfirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK){
                    Integer employeeId = Integer.parseInt(lblEmployeeTitle.getText());
                    EmployeeBo employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
                    if(employeeService.deleteEmployee(employeeId)){
                        new Alert(Alert.AlertType.INFORMATION,"Employee deleted !").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Employee Not deleted !").show();
                    }
                    loadEmployeeTable();
                    clearAll();
                }
            });
        }
    }

    @FXML
    void btnEscapeProductOnAction(ActionEvent event) {
        if (btnEscapeProduct.getText().equals("Clear") && btnSubmitProduct.getText().equals("Add")){
            clearAll();
            return;
        }
        if (btnEscapeProduct.getText().equals("Delete") && btnSubmitProduct.getText().equals("Update")){
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you want delete?");
            alertConfirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK){
                    Integer productId = Integer.parseInt(lblProductTitle.getText());
                    ProductBo productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
                    if(productService.deleteProduct(productId)){
                        new Alert(Alert.AlertType.INFORMATION,"Product Deleted !").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Product Not Deleted !").show();
                    }
                    loadProductTable();
                    clearAll();
                }
            });
        }
    }

    @FXML
    void btnEscapeSupplierOnAction(ActionEvent event) {
        if (btnEscapeSupplier.getText().equals("Clear") && btnSubmitSupplier.getText().equals("Add")){
            clearAll();
            return;
        }
        if (btnEscapeSupplier.getText().equals("Delete") && btnSubmitSupplier.getText().equals("Update")){
            String supplierName = txtSupplierName.getText();
            String supplierEmail = txtSupplierEmail.getText();
            if (isNullOrEmptyOrBlank(supplierName, supplierEmail)){
                new Alert(Alert.AlertType.WARNING,"Empty field founded!").show();
                return;
            }
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you want delete?");
            alertConfirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK){
                    Integer supplierId = Integer.parseInt(lblSupplierTitle.getText());
                    SupplierBo supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
                    if(supplierService.deleteSupplier(supplierId)){
                        new Alert(Alert.AlertType.INFORMATION,"Supplier deleted !").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Supplier Not deleted !").show();
                    }
                    loadSupplierTable();
                    clearAll();
                }
            });
        }
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
    void btnSelectImgOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("SVG", "*.svg")
        );
        File file = fileChooser.showOpenDialog(homepage.getScene().getWindow());
        if (file != null){
            productImg.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    void btnSelectImgOnMouseEntered(MouseEvent event) {
        btnSelectImg.setText("Edit");
        btnSelectImg.setStyle("-fx-background-color: #A6A8AB; -fx-background-radius: 15;");
    }

    @FXML
    void btnSelectImgOnMouseExited(MouseEvent event) {
        btnSelectImg.setText("");
        btnSelectImg.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 15;");
    }

    @FXML
    void btnSubmitEmployeeOnAction(ActionEvent event) {
        String employeeName = txtEmployeeName.getText();
        String employeeAddress = txtEmployeeAddress.getText();
        String employeeEmail = txtEmployeeEmail.getText();
        if (isNullOrEmptyOrBlank(employeeName, employeeAddress, employeeEmail)){
            new Alert(Alert.AlertType.WARNING,"Empty field founded!").show();
            return;
        }
        if (btnEscapeEmployee.getText().equals("Clear") && btnSubmitEmployee.getText().equals("Add")){
            Employee newEmployee = new Employee(null,employeeName,employeeAddress,employeeEmail);
            EmployeeBo employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
            if(employeeService.addEmployee(newEmployee)){
                new Alert(Alert.AlertType.INFORMATION,"Employee Added !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee Not Added !").show();
            }
            loadEmployeeTable();
            clearAll();
            return;
        }
        if (btnEscapeEmployee.getText().equals("Delete") && btnSubmitEmployee.getText().equals("Update")){
            Integer employeeId = Integer.parseInt(lblEmployeeTitle.getText());
            Employee updateEmployee = new Employee(employeeId,employeeName,employeeAddress,employeeEmail);
            EmployeeBo employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
            if(employeeService.updateEmployee(updateEmployee)){
                new Alert(Alert.AlertType.INFORMATION,"Employee Updated !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee Not Updated !").show();
            }
            loadEmployeeTable();
            clearAll();
        }
    }

    @FXML
    void btnSubmitProductOnAction(ActionEvent event) {
        String productImgSrc = productImg.getImage().getUrl();
        String productName = txtProductName.getText();
        if (isNullOrEmptyOrBlank(productName)){
            new Alert(Alert.AlertType.WARNING,"Product name empty!").show();
            return;
        }
        ProductSize productSize = cmbProductSize.getValue();
        double productUnitPrice;
        try {
            productUnitPrice = Double.parseDouble(txtProductUnitPrice.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING,"Invalid Unit Price!").show();
            return;
        }
        int productQtyOnHand = spinnerProductQtyOnHand.getValue();
        if (btnEscapeProduct.getText().equals("Clear") && btnSubmitProduct.getText().equals("Add")){
            Product newProduct = new Product(null, productName,productSize,productUnitPrice,productQtyOnHand,productImgSrc);
            ProductBo productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
            if(productService.addProduct(newProduct)){
                new Alert(Alert.AlertType.INFORMATION,"Product Added !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product Not Added !").show();
            }
            loadProductTable();
            clearAll();
            return;
        }
        if (btnEscapeProduct.getText().equals("Delete") && btnSubmitProduct.getText().equals("Update")){
            Integer productId = Integer.parseInt(lblProductTitle.getText());
            Product updateProduct = new Product(productId, productName,productSize,productUnitPrice,productQtyOnHand,productImgSrc);
            ProductBo productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
            if(productService.updateProduct(updateProduct)){
                new Alert(Alert.AlertType.INFORMATION,"Product Updated !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product Not Updated !").show();
            }
            loadProductTable();
            clearAll();
        }
    }

    @FXML
    void btnSubmitSupplierOnAction(ActionEvent event) {
        String supplierName = txtSupplierName.getText();
        String supplierEmail = txtSupplierEmail.getText();
        if (isNullOrEmptyOrBlank(supplierName, supplierEmail)){
            new Alert(Alert.AlertType.WARNING,"Empty field founded!").show();
            return;
        }
        if (btnEscapeSupplier.getText().equals("Clear") && btnSubmitSupplier.getText().equals("Add")){
            Supplier newSupplier = new Supplier(null, supplierName, supplierEmail);
            SupplierBo supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
            if(supplierService.addSupplier(newSupplier)){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Added !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier Not Added !").show();
            }
            loadSupplierTable();
            clearAll();
            return;
        }
        if (btnEscapeSupplier.getText().equals("Delete") && btnSubmitSupplier.getText().equals("Update")){
            Integer supplierId = Integer.parseInt(lblSupplierTitle.getText());
            Supplier updateSupplier = new Supplier(supplierId, supplierName, supplierEmail);
            SupplierBo supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
            if(supplierService.updateSupplier(updateSupplier)){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Updated !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
            }
            loadSupplierTable();
            clearAll();
        }
    }

    private void loadProductTable(){
        ProductBo productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        tblProduct.setItems(productService.getAll());
    }

    private void loadSupplierTable(){
        SupplierBo supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        tblSupplier.setItems(supplierService.getAll());
    }

    private void loadEmployeeTable(){
        EmployeeBo employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        tblEmployee.setItems(employeeService.getAll());
    }

    private void clearAll(){
        switch (tabPane.getSelectionModel().getSelectedItem().getText()){
            case "Order":
                break;
            case "Product":
                productImg.setImage(new Image("img/logo.png"));
                lblProductTitle.setText("New Product");
                txtProductName.setText(null);
                cmbProductSize.setValue(ProductSize.NONE);
                txtProductUnitPrice.setText(null);
                spinnerValueFactory.setMax(999);
                spinnerProductQtyOnHand.setValueFactory(spinnerValueFactory);
                break;
            case "Supplier":
                lblSupplierTitle.setText("New Supplier");
                txtSupplierName.setText(null);
                txtSupplierEmail.setText(null);
                btnSubmitSupplier.setText("Add");
                btnEscapeSupplier.setText("Clear");
                break;
            case "Employee":
                lblEmployeeTitle.setText("New Employee");
                txtEmployeeName.setText(null);
                txtEmployeeAddress.setText(null);
                txtEmployeeEmail.setText(null);
                btnSubmitEmployee.setText("Add");
                btnEscapeEmployee.setText("Clear");
                break;
        }
    }

    private boolean isNullOrEmptyOrBlank(String... text){
        for (String txt : text) {
            if ((txt == null) || txt.isEmpty() || txt.isBlank()){
                return true;
            }
        }
        return false;
    }
}
