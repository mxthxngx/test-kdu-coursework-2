package org.handson.service;

import jakarta.annotation.PostConstruct;
import org.handson.components.InventoryStore;
import org.handson.components.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
@Import({SpeakerService.class, TyreService.class})
public class VehicleConfig {
    private final SpeakerService speakerService;
    private final TyreService tyreService;

    public VehicleConfig(SpeakerService speakerService,TyreService tyreService) {
        this.speakerService = speakerService;
        this.tyreService = tyreService;
    }
    Random random = new Random();
    /**
     * Generates a new Vehicle with a random speaker, random tyre, and a randomly generated price.
     *
     * @return         	the newly generated Vehicle
     */
    @Bean
    @Scope("prototype")
    public Vehicle vehicleGenerator()
    {
        int generatedPrice  = random.nextInt(1000);
        var tyre = tyreService.getRandomTyre();
        var speaker = speakerService.getRandomSpeaker();
        return new Vehicle(speaker,tyre,tyre.getPrice()+generatedPrice+speaker.getPrice());
  }
    @Bean(name="factory1vehicle")
    @Scope("prototype")
    public Vehicle vehicleGeneratorFactory1()
    {
        int generatedPrice  = random.nextInt(1000);
        var tyre = tyreService.getRandomTyre();
        var speaker = speakerService.getRandomSpeaker();
        return new Vehicle(speaker,tyre,tyre.getPrice()*1.1+generatedPrice+speaker.getPrice());

    }
    @Bean(name="factory2vehicle")
    @Scope("prototype")
    public Vehicle vehicleGeneratorFactory2()
    {
        int generatedPrice  = random.nextInt(1000);
        var tyre = tyreService.getRandomTyre();
        var speaker = speakerService.getRandomSpeaker();
        return new Vehicle(speaker,tyre,tyre.getPrice()*5.1+generatedPrice+speaker.getPrice());

    }
    @Bean("factory1")

    @PostConstruct
    public InventoryStore getInstanceFactory1()
    {
        return new InventoryStore();
    }
    @Bean("factory2")
    @PostConstruct
    public InventoryStore getInstanceFactory2()
    {
        return new InventoryStore();
    }

    @Bean
    public FactoryService1 factoryService1()
    {
        return new FactoryService1(getInstanceFactory1());
    }

    @Bean
    public FactoryService2 factoryService2()
    {
        return new FactoryService2(getInstanceFactory2());
    }
}

