package space.aiyo.var;

public enum Documents {
  DOTA_HERO("dotaHero"),
  DOTA_ITEM("dotaItem"),
  DOTA_MATCH("dotaMatch"),;

  private String name;

  Documents(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
