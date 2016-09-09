{
  "type": "script",
  "seleniumVersion": "2",
  "formatVersion": 2,
  "steps": [
    {
      "type": "get",
      "url": "https://www.google.co.in/?gfe_rd=cr&ei=fUzSV9R06cDyB5_cisgF&gws_rd=ssl"
    },
    {
      "type": "setElementText",
      "locator": {
        "type": "id",
        "value": "lst-ib"
      },
      "text": "Sauce Labs blogs"
    },
    {
      "type": "sendKeysToElement",
      "locator": {
        "type": "id",
        "value": "lst-ib"
      },
      "text": "\n"
    },
    {
      "type": "clickElement",
      "locator": {
        "type": "link text",
        "value": "Blog | Sauce Labs"
      }
    },
    {
      "type": "clickElement",
      "locator": {
        "type": "link text",
        "value": "JUnit"
      }
    },
    {
      "type": "clickElement",
      "locator": {
        "type": "link text",
        "value": "READ MORE"
      }
    },
    {
      "type": "clickElement",
      "locator": {
        "type": "link text",
        "value": "API documentation"
      }
    },
    {
      "type": "switchToWindow",
      "name": ""
    },
    {
      "type": "switchToWindow",
      "name": "New Tab"
    }
  ],
  "data": {
    "configs": {},
    "source": "none"
  },
  "inputs": [],
  "timeoutSeconds": 60
}