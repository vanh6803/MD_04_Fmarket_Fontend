package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductDetail implements Serializable {
    @SerializedName("_id")
    private String id;
    private Store store_id;
    private ProductType category_id;
    private String name;
    private List<String> image;
    private String description;
    private String status;
    private boolean discounted;
    private boolean is_active;
    private String screen;
    private String camera;
    private String chipset;
    private String cpu;
    private String gpu;
    private int ram;
    private int rom;
    private String operatingSystem;
    private String battery;
    private int weight;
    private String connection;
    private String specialFeature;
    private String manufacturer;
    private String other;
    private List<OptionProduct> option;
    private List<ProductRate> product_review;

    public ProductDetail() {
    }

    public ProductDetail(String id, Store store_id, ProductType category_id, String name, List<String> image, String description, String status, boolean discounted, boolean is_active, String screen, String camera, String chipset, String cpu, String gpu, int ram, int rom, String operatingSystem, String battery, int weight, String connection, String specialFeature, String manufacturer, String other, List<OptionProduct> option, List<ProductRate> product_review) {
        this.id = id;
        this.store_id = store_id;
        this.category_id = category_id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.status = status;
        this.discounted = discounted;
        this.is_active = is_active;
        this.screen = screen;
        this.camera = camera;
        this.chipset = chipset;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.rom = rom;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.weight = weight;
        this.connection = connection;
        this.specialFeature = specialFeature;
        this.manufacturer = manufacturer;
        this.other = other;
        this.option = option;
        this.product_review = product_review;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id='" + id + '\'' +
                ", store_id=" + store_id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", discounted=" + discounted +
                ", is_active=" + is_active +
                ", screen='" + screen + '\'' +
                ", camera='" + camera + '\'' +
                ", chipset='" + chipset + '\'' +
                ", cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram=" + ram +
                ", rom=" + rom +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", battery='" + battery + '\'' +
                ", weight=" + weight +
                ", connection='" + connection + '\'' +
                ", specialFeature='" + specialFeature + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", other='" + other + '\'' +
                ", option=" + option +
                ", product_review=" + product_review +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Store getStore_id() {
        return store_id;
    }

    public void setStore_id(Store store_id) {
        this.store_id = store_id;
    }

    public ProductType getCategory_id() {
        return category_id;
    }

    public void setCategory_id(ProductType category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<OptionProduct> getOption() {
        return option;
    }

    public void setOption(List<OptionProduct> option) {
        this.option = option;
    }

    public List<ProductRate> getProduct_review() {
        return product_review;
    }

    public void setProduct_review(List<ProductRate> product_review) {
        this.product_review = product_review;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }
}
