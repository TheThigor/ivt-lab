package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore mocPrimer; 
  TorpedoStore mocSeconder; 

  @BeforeEach
  public void init(){
    mocPrimer = mock(TorpedoStore.class);
    mocSeconder = mock(TorpedoStore.class);
    this.ship = new GT4500(mocPrimer, mocSeconder);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mocPrimer.isEmpty()).thenReturn(false); 
    when(mocPrimer.getTorpedoCount()).thenReturn(10);
    when(mocPrimer.fire(1)).thenReturn(true); 
    when(mocSeconder.isEmpty()).thenReturn(false); 
    when(mocSeconder.getTorpedoCount()).thenReturn(10);
    when(mocSeconder.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mocPrimer, times(1)).fire(1); 
    verify(mocSeconder, times(0)).fire(1); 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mocPrimer.isEmpty()).thenReturn(false); 
    when(mocPrimer.getTorpedoCount()).thenReturn(10);
    when(mocPrimer.fire(1)).thenReturn(true); 
    when(mocSeconder.isEmpty()).thenReturn(false); 
    when(mocSeconder.getTorpedoCount()).thenReturn(10);
    when(mocSeconder.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mocPrimer, times(1)).fire(1); 
    verify(mocSeconder, times(1)).fire(1); 
  }

 @Test
 public void fireTorpedo_First_Then_Second_Success(){
  //Arrange
  when(mocPrimer.isEmpty()).thenReturn(false); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(false); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  //Act
  boolean result = ship.fireTorpedo(FiringMode.SINGLE);
  if(result)
    result = ship.fireTorpedo(FiringMode.SINGLE);  

  // Assert
  assertEquals(true, result); 
  verify(mocPrimer, times(1)).fire(1); 
  verify(mocSeconder, times(1)).fire(1); 
 } 

 @Test 
 public void fireTorpedo_Primary_Empty(){
  //Arrange
  when(mocPrimer.isEmpty()).thenReturn(true); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(false); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  //Act
  boolean result = ship.fireTorpedo(FiringMode.SINGLE);

  // Assert
  assertEquals(true, result); 
  verify(mocSeconder, times(1)).fire(1); 
  verify(mocPrimer, times(1)).isEmpty(); 

 } 

@Test
public void fireTorpedo_Both_Empty(){
  //Arrange
  when(mocPrimer.isEmpty()).thenReturn(true); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(true); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  //Act
  boolean result = ship.fireTorpedo(FiringMode.SINGLE);

  // Assert
  assertEquals(false, result);
  verify(mocPrimer, times(1)).isEmpty(); 
  verify(mocSeconder, times(1)).isEmpty(); 
} 
@Test
public void fireTorpedo_Twice_Primary_Empty(){
  //Arrange
  when(mocPrimer.isEmpty()).thenReturn(true); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(false); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  //Act
  boolean result = ship.fireTorpedo(FiringMode.SINGLE);
  if(result)
    result = ship.fireTorpedo(FiringMode.SINGLE);  

  // Assert
  assertEquals(true, result);
  verify(mocSeconder, times(2)).fire(1); 
  verify(mocPrimer, times(2)).isEmpty(); 
  
} 
@Test
public void fireTorpedo_Twice_Secunder_Empty(){
  //Arrange
  when(mocPrimer.isEmpty()).thenReturn(false); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(true); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  //Act
  boolean result = ship.fireTorpedo(FiringMode.SINGLE);
  if(result)
    result = ship.fireTorpedo(FiringMode.SINGLE);  

  // Assert
  assertEquals(true, result);
  verify(mocPrimer, times(2)).fire(1); 
  verify(mocSeconder, times(1)).isEmpty(); 
} 
@Test
public void fireTorpedo_FireAll_BothEmpty(){
  when(mocPrimer.isEmpty()).thenReturn(true); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(true); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  boolean result = ship.fireTorpedo(FiringMode.ALL);

  assertEquals(false, result); 
  verify(mocPrimer, times(1)).isEmpty();  
   
}
@Test
public void fireTorpedo_FireAll_SeconderEmpty(){
  when(mocPrimer.isEmpty()).thenReturn(false); 
  when(mocPrimer.fire(1)).thenReturn(true); 
  when(mocSeconder.isEmpty()).thenReturn(true); 
  when(mocSeconder.fire(1)).thenReturn(true); 

  boolean result = ship.fireTorpedo(FiringMode.ALL);
  verify(mocPrimer, times(1)).isEmpty();  
  verify(mocSeconder, times(1)).isEmpty();  
} 







}
