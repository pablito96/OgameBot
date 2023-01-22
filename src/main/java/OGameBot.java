import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.devtools.v85.cachestorage.model.Header;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class OGameBot {

    public static WebDriver driver;
    public static int attemptsCount=1;
    public static int researchAttemptsCount=0;
    public static JavascriptExecutor js;

    public static Integer energy;

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "geckodriver-v0.30.0-linux32/geckodriver");
//        System.setProperty("webdriver.gecko.driver", "geckodriver-v0.31.0-win64/geckodriver.exe");


        FirefoxOptions firefoxOptions = new FirefoxOptions().setHeadless(true);
        log.info("hello world");
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().setSize(new Dimension(1920,1080));
//        driver.manage().window().fullscreen();
//        driver.manage().window().maximize();


        js = (JavascriptExecutor) driver;
        Map<String, Object> vars = new HashMap<String, Object>();

        String mainUrl ="https://s184-pl.ogame.gameforge.com/game/index.php?page=ingame&component=overview&relogin=1";

        String baseUrl = "https://s184-pl.ogame.gameforge.com/game/index.php?page=ingame";
        String supplies = "&component=supplies";
        String facilities = "&component=facilities";
        String research = "&component=research";
        String shipyard = "&component=shipyard";
        String defenses = "&component=defenses";
        String galaxy = "&component=galaxy";

        String planet2 = "&cp=33670134";
        String planet4 = "&cp=33673970";


//        Cookie ck = new Cookie("PHPSESSID", "42926b7b65278458e0ae30e19e15");
//        Cookie ck1 = new Cookie("gf-token-production", "32abe3ca-fc42-4046-a2ee-da789e5cc8a7");
//        Cookie ck2 = new Cookie("prsess_105317", "9b7a9c91a570fe7c8d06b9e24456e609");
//        driver.manage().addCookie(ck);
//        driver.manage().addCookie(ck1);
//        driver.manage().addCookie(ck2);

//        Cookie
//
//        driver.manage().addCookie();

//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        Header header = new Header("Cookie", "maximizeId=null; gf-cookie-consent-4449562312=|7|1; prsess_105317=e71f89ae74f107e97fa3c5d5bfcf576d; gf-token-production=32abe3ca-fc42-4046-a2ee-da789e5cc8a7; PHPSESSID=42926b7b65278458e0ae30e19e15cfdf209081a9");
//        firefoxOptions.add;

//        driver.get("https://s184-pl.ogame.gameforge.com/game/index.php?page=ingame&component=overview&relogin=1");
        driver.get(baseUrl);
//        sleep(10000);
//        driver.quit();

        sleep(1000);

//        driver
        WebElement cookieBanner = driver.findElement(By.className("cookiebanner3"));

        js.executeScript("arguments[0].setAttribute('style', 'display:none;')", cookieBanner);


//        driver.findElement(By.id("loginRegisterTabs")).findElement(By.className("tabsList")).findElements(By.tagName("span")).get(1).click();
        WebElement webElement = driver.findElement(By.id("loginRegisterTabs"));
        log.info(webElement.getText());

        sleep(1000);
        webElement = webElement.findElement(By.className("tabsList"));
        log.info(webElement.getText());

        sleep(1000);
        WebElement loginTab = webElement.findElements(By.tagName("span")).get(0);
        log.info("tutaj {}", loginTab.getText());
        loginTab.click();


        WebElement logRegTab = driver.findElement(By.id("loginRegisterTabs"));

        log.info(logRegTab.getText());


        sleep(1000);
        List<WebElement> loginInputs = logRegTab.findElements(By.tagName("input"));


        loginInputs.get(0).sendKeys("****");
        loginInputs.get(1).sendKeys("****");
        sleep(1000);
//
        WebElement submitButton = logRegTab.findElement(By.tagName("button"));
        log.info(submitButton.getText());
        sleep(2000);

        if(!submitButton.getText().equals("Login")){
            log.info("Login button not found");
            driver.quit();
        }
        submitButton.click();

        sleep(1000);

        driver.get(driver.getCurrentUrl());

        sleep(2000);
        List<WebElement> chooseServerButtons = driver.findElements(By.tagName("button"));

        sleep(2000);
        log.info("Welcome page buttons:");
        chooseServerButtons.forEach(x-> log.info(x.getText()));

        WebElement lastGame = chooseServerButtons.stream().filter(x->x.getText().contains("Ostatnia gra")).collect(Collectors.toList()).get(0);

        sleep(1000);
        try {
            lastGame.click();
        }
        catch(ElementClickInterceptedException e){
            js.executeScript("arguments[0].setAttribute('style', 'display:none;')", driver.findElement(By.className("cookiebanner3")));
            lastGame.click();
        }

        sleep(2000);
        driver.get(driver.getCurrentUrl());

        sleep(3000);
        driver.get(baseUrl+supplies+planet4);
        log.info(driver.getCurrentUrl());

//        Map<String, WebElement> producers = new HashMap<>();
//        Map<String, WebElement> producers = itemsToMap("producers");

//        sleep(3000);
//        driver.findElement(By.id("producers")).findElements(By.tagName("li")).forEach(el -> producers.put(el.getAttribute("aria-label"), el));
//
//        producers.forEach( (k,v) -> log.info(k));
        sleep(1000);

        driver.switchTo().window(driver.getWindowHandles().stream().toList().get(1));
        sleep(1000);
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().stream().toList().get(0));

        sleep(1000);


//        WebDriver driver1 = new FirefoxDriver(firefoxOptions);
//        WebDriver driver2 = new FirefoxDriver(firefoxOptions);
//        WebDriver driver3 = new FirefoxDriver(firefoxOptions);
//
////        driver1.get();
//
//
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrl+facilities+planet4);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrl+research);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrl+defenses);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrl+galaxy);

        Set<String> handles = driver.getWindowHandles();




        while(true) {

            log.info("Metal: {}", driver.findElement(By.id("resources_metal")).getText());
            log.info("Kryształ: {}", driver.findElement(By.id("resources_crystal")).getText());
            log.info("Deuter: {}", driver.findElement(By.id("resources_deuterium")).getText());

            energy = Integer.parseInt(driver.findElement(By.id("resources_energy")).getText());
            log.info("Energia: {}", energy);

            try{
                WebElement energy =driver.findElement(By.cssSelector("span#resources_energy.overmark"));
                log.info("Energy level: {}", driver.findElement(By.cssSelector("span#resources_energy")));
                tryResearchTechnologies((String)handles.toArray()[0], "producers",  Set.of("Elektrownia słoneczna"));
//                tryResearchTechnologies((String)handles.toArray()[0], "producers",  Set.of("Magazyn"));
            }
            catch (NoSuchElementException e){
                log.info("Sufficient amount of energy");
            }

//            tryBuildProducers((String)handles.toArray()[0], "producers",  Set.of("Magazyn" /*, "Zbiornik"*/));
            tryResearchTechnologies((String)handles.toArray()[0], "producers",  Set.of("Kopalnia" /*, "Ekstraktor"*/));
//            tryResearchTechnologies((String)handles.toArray()[1], "technologies",  Set.of("Stocznia" , "Laboratorium", "Fabryka"/*, "Dok"*/));
//            tryResearchTechnologies((String)handles.toArray()[0], "producers",  Set.of("Magazyn" /*, "Zbiornik"*/));
//
//            tryResearchTechnologies((String)handles.toArray()[2], "technologies",  Set.of(""));

//            searchAbandonedPlanets((String)handles.toArray()[4]);
//            searchInactivePlayerPlanets((String)handles.toArray()[4], 3);

            sleep(1*1000);
        }





    }

    private static void searchAbandonedPlanets(String handle){
        driver.switchTo().window(handle);
        driver.navigate().refresh();

//        WebElement galaform = driver.findElement(By.cssSelector("form.galaform"));
//        driver.findElement(By.cssSelector("input#galaxy_input")).sendKeys();
//        driver.findElement(By.cssSelector("input#system_input")).sendKeys();
        for(int i=1; i<=6; i++){
//            i=4;
//            int i = (int)(Math.random()*5) +1;
            log.info("Searching abandoned planets in {} galaxy.", i);
            for(int j=1; j<=499; j++){
                driver.findElement(By.cssSelector("input#galaxy_input")).sendKeys(String.valueOf(i));
                driver.findElement(By.cssSelector("input#system_input")).sendKeys(String.valueOf(j));
//                galaform.submit();
//                sleep(500);
//                driver.findElement(By.className("galaform")).submit();
                WebElement submitButton = driver.findElement(By.id("galaxyHeader")).findElement(By.className("btn_blue"));
//                log.info(submitButton.getText());

                submitButton.click();
                sleep(200);

                String galaxyTable = driver.findElement(By.className("galaxyTable")).getText();
                if(galaxyTable.contains("Porzucona planeta")){
                    log.info("###############################");
                    log.info("Porzucona planeta w {} galaktyce, {} układzie słonecznym", i, j);
                    log.info("###############################");
                }

            }
//            break;
        }

    }

    private static void searchInactivePlayerPlanets(String handle, Integer galaxy){
        driver.switchTo().window(handle);
        driver.navigate().refresh();

//        WebElement galaform = driver.findElement(By.cssSelector("form.galaform"));
//        driver.findElement(By.cssSelector("input#galaxy_input")).sendKeys();
//        driver.findElement(By.cssSelector("input#system_input")).sendKeys();

            log.info("Searching inactive player planets in {} galaxy.", galaxy);
            for(int i=1; i<=499; i++){
                driver.findElement(By.cssSelector("input#galaxy_input")).sendKeys(String.valueOf(i));
                driver.findElement(By.cssSelector("input#system_input")).sendKeys(String.valueOf(galaxy));

                WebElement submitButton = driver.findElement(By.id("galaxyHeader")).findElement(By.className("btn_blue"));

                submitButton.click();
                sleep(200);

                List<WebElement> galaxyRows = driver.findElement(By.id("galaxyContent")).findElements(By.className("ctContentRow"));
                for(WebElement row : galaxyRows) {
                   String cssClass = row.getAttribute("class");

                   if(cssClass.contains("vacation")){
                       break;
                   }

                    if (cssClass.contains("inactive")) {
                        log.info("###############################");
                        log.info("Inactive player planet in coordinates: [{},{},{}]", galaxy, i, row.findElement(By.className("cellPosition")).getText());
                        log.info("###############################");
                    }
                }


        }

    }

    public static void tryResearchTechnologies(String handle, String groupId, Set<String> whatToBuild){
        driver.switchTo().window(handle);
        driver.navigate().refresh();
        Map<String, WebElement> producers = itemsToMap(groupId, whatToBuild);

        List<String> tmp = producers.keySet().stream().toList();
        ArrayList<String> keys = new ArrayList<>(tmp);
        Collections.shuffle(keys, new Random());

        String key;
        for(int i=0; i< producers.size(); i++){

            countDown(groupId);

            int toDevelop = (researchAttemptsCount+i)% producers.size();

            key = keys.get(toDevelop);
            try {
                producers.entrySet().stream().toList().get(toDevelop).getValue().findElement(By.className("upgrade")).click();


                log.info("############");
                log.info("Building {}", key);
                log.info("############");
                researchAttemptsCount++;
                break;

            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("Failed to build = {}", key);
                sleep(2000);


            }
        }
    }


    private static void tryBuildProducers(String handle, String groupId, Set<String> whatToBuild){

        driver.switchTo().window(handle);
        driver.navigate().refresh();
        Map<String, WebElement> producers = itemsToMap(groupId, whatToBuild);

        List<String> tmp = producers.keySet().stream().toList();
        ArrayList<String> keys = new ArrayList<>(tmp);
        Collections.shuffle(keys, new Random());

        if(groupId.equals("producers")) {
            try{
                String satelitesBuildTime = driver.findElement(By.id("producers")).findElement(By.className("solarSatellite")).findElement(By.className("countdown")).getText();
                log.info("Satelites in build: {}", satelitesBuildTime);
                return;
            }
            catch(NoSuchElementException e){

            }

        }

        String key;
        for(int i=0; i< producers.size(); i++){

            countDown(groupId);

            key = keys.get(i);
            try {
//                producers.entrySet().stream().toList().get(i).getValue().findElement(By.className("upgrade")).click();
                producers.entrySet().stream().toList().get(i).getValue().click();
                Integer additionalEnergyConsumption= Integer.parseInt( driver.findElement(By.className("additional_energy_consumption")).findElement(By.tagName("span")).getText());
                log.info("Additonal energy consumption: {}", additionalEnergyConsumption);
                if(additionalEnergyConsumption<energy){
                    producers.entrySet().stream().toList().get(i).getValue().findElement(By.className("upgrade")).click();
                }
                else{
                    driver.findElement(By.className("solarSatellite")).click();
                    sleep(200);
                    Integer energyPerSatellite = Integer.parseInt(driver.findElement(By.className("energy_production")).findElement(By.className("bonus")).getAttribute("data-value"));
                    log.info("Energy per satellite: {}", energyPerSatellite);
                    int satelliteAmountToBuuld = (additionalEnergyConsumption - energy)/energyPerSatellite +1;
                    driver.findElement(By.id("build_amount")).sendKeys(satelliteAmountToBuuld+"");
                    driver.findElement(By.id("technologydetails")).findElement(By.className("upgrade")).click();
                    key= satelliteAmountToBuuld +" Solar satellite";
                }

                log.info("############");
                log.info("Building {}", key);
                log.info("############");
                attemptsCount++;
                break;

            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("Failed to build = {}", key);
                sleep(2000);


            }
        }

    }

    private static Map<String, WebElement> itemsToMap(String groupId, Set<String> whatToBuild){
        Map<String, WebElement> itemsMap = new HashMap<>();
//        String key;
        sleep(3000);
        driver.findElement(By.id(groupId)).findElements(By.tagName("li")).forEach(el ->{
                    String key =el.getAttribute("aria-label");
                    if(whatToBuild!=null) {
                        for (String name : whatToBuild) {
                            if (key.contains(name)) {
                                itemsMap.put(key, el);
                            }
                        }
                    }
                    else{
                        itemsMap.put(key, el);
                    }
        } );

        itemsMap.forEach( (k,v) -> log.info(k));

        return itemsMap;
    }


    private static void sleep(long mills){
        try {
//            driver.wait(2000);
            Thread.sleep(mills);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void countDown(String groupId){

        for(var li : driver.findElement(By.id(groupId)).findElements(By.tagName("li")) ){
              try{
                  String countDown =li.findElement(By.className("countdown")).getText();
                  String key = li.getAttribute("aria-label");
                  log.info("Currently in develop: {}", key);
                  log.info("Time to end current build/research: {}", countDown);
                  return;
              }
              catch (NoSuchElementException e){
              }
        }
        log.info("No buildings/reaserch in develop");
    }

}


