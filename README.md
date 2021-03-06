# config-framework

---

## TODO

- [ ] Creating transformers for new classes
- [ ] Support creation of JSON files

---

## Installation

### Maven

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.vscyther</groupId>
    <artifactId>config-framework</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
    implementation 'com.github.vscyther:config-framework:1.0'
}
```

---

## Usage

### Kotlin

```kotlin
@ConfigPath("inventories/inventory_1.yml")
class Inventory_1 {

    @ConfigKey("title")
    val title: String? = null

    @ConfigKey("rows")
    val rows: Int = 0

}
```

### Java

```java

@Getter // lombok
@ConfigPath("messages.yml")
class Messages {

    @ConfigKey("messages.message_1")
    private String message_1;

    @ConfigKey("messages.message_2")
    private String message_2;

}
```

### Registering

```java
class Main {

    private Inventory_1 inventory_1;
    private Messages messages;

    @Override
    public void onEnable() {
        final ConfigFrame cf = new ConfigFrame(this);

        cf.load(
                (inventory_1 = new Inventory_1()),
                (messages = new Messages())
        );

        final String title = inventory_1.getTitle();
        final int rows = inventory_1.getRows();

        final String message_1 = messages.getMessage_1();
        final String message_2 = messages.getMessage_2();
    }

}
```
