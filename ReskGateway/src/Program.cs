using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Ocelot.DependencyInjection;
using Serilog;

namespace ReskGateway
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        public static IWebHostBuilder CreateHostBuilder(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .UseSerilog((hostingContext, loggerConfiguration) => loggerConfiguration
                    .ReadFrom.Configuration(hostingContext.Configuration)
                    .Enrich.FromLogContext()
                    .WriteTo.Console())
                .ConfigureAppConfiguration((hostingContext, config) =>
                {
                    string configPath = Path.Combine(hostingContext.HostingEnvironment.ContentRootPath, "Configuration");
                    configPath = Path.Combine(configPath, hostingContext.HostingEnvironment.EnvironmentName, "ocelot.json");
                    config.AddJsonFile(configPath, optional: false, reloadOnChange: true);
                    config.AddEnvironmentVariables();
                });
            
    }
}
